package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.Language
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitReq
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RegFormReqData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RequestType
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.PANOnly
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitReqMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.RegFormReqDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.CryptoDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.PANOnlyMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageWrapperMapper
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.common.ThumbsMismatch
import aleksandr.fedotkin.buyercryptomoney.domain.common.UnknownRRPID
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.PANOnlyModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.RegFormReqDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageIDModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CertificateRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.EXHRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import java.math.BigInteger
import kotlinx.serialization.KSerializer
import kotlin.random.Random

class CertificateRepositoryImpl(
    private val messageWrapperMapper: MessageWrapperMapper,
    private val cardCInitReqMapper: CardCInitReqMapper,
    private val cardCInitResMapper: CardCInitResMapper,
    private val networkAPI: NetworkAPI,
    private val errorRepository: ErrorRepository,
    private val jsonMapper: JsonMapper,
    private val keyRepository: KeyRepository,
    private val exhRepository: EXHRepository,
    private val regFormReqDataMapper: RegFormReqDataMapper,
    private val panOnlyMapper: PANOnlyMapper,
    private val messageWrapperRepository: MessageWrapperRepository,
    private val cryptoDataMapper: CryptoDataMapper
) : CertificateRepository {

    override suspend fun getCertificate(number: String) {
        val messageWrapperModelCardCInitReq = createMessageWrapperModel()
        val messageWrapperCardCInitReq = messageWrapperMapper.map(
            messageWrapperModel = messageWrapperModelCardCInitReq, map = cardCInitReqMapper::map
        )
        val messageWrapperCardCInitReqJson = jsonMapper.objectToString(
            data = messageWrapperCardCInitReq,
            serializer = MessageWrapper.serializer(CardCInitReq.serializer())
        )
        val unknownMessageWrapperJson =
            networkAPI.sendCardCInitReq(messageWrapperJson = messageWrapperCardCInitReqJson)
        val messageWrapperCardCInitRes =
            checkCardCInitRes(unknownMessageWrapperJson = unknownMessageWrapperJson)
        val messageWrapperModelCardCInitRes = messageWrapperMapper.map(
            dto = messageWrapperCardCInitRes, map = cardCInitResMapper::map
        )
        checkCardCInitRes(
            messageWrapperModel = messageWrapperModelCardCInitRes,
            cardCInitReqModel = messageWrapperModelCardCInitReq.messageModel
        )
        val certificate =
            keyRepository.createCertificate(certificate = messageWrapperModelCardCInitRes.messageModel.cardCInitResTBS.caeThumb)
        val panOnlyModel = createPANOnlyModel(number = number)
        val regFormReqDataModel =
            createRegFormReqDataModel(cardCInitResModel = messageWrapperModelCardCInitRes.messageModel)
        val cryptoDataModel = exhRepository.encrypt(
            r = certificate.publicKey,
            t = regFormReqDataModel,
            p = panOnlyModel,
            mapT = regFormReqDataMapper::map,
            mapP = panOnlyMapper::map,
            tSerializer = RegFormReqData.serializer(),
            pSerializer = PANOnly.serializer()
        )
        val messageWrapperModelCryptoData = createMessageWrapperModel(
            cryptoDataModel = cryptoDataModel,
            messageWrapperModel = messageWrapperModelCardCInitRes,
            rrpid = regFormReqDataModel.rrpID
        )
        val messageWrapperCryptoData = messageWrapperMapper.map(
            messageWrapperModel = messageWrapperModelCryptoData,
            map = cryptoDataMapper::map
        )
        val messageWrapperRegFormResJson = networkAPI.regForm(
            messageWrapperJson = jsonMapper.objectToString(
                data = messageWrapperCryptoData,
                serializer = MessageWrapper.serializer(CryptoData.serializer())
            )
        )
    }

    private fun createPANOnlyModel(number: String): PANOnlyModel {
        return PANOnlyModel(
            pan = BigInteger(number),
            exNonce = generateNewNumber()
        )
    }

    private fun createRegFormReqDataModel(cardCInitResModel: CardCInitResModel): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = generateNewNumber(),
            lidEE = cardCInitResModel.cardCInitResTBS.lidEE,
            challEE2 = generateNewNumber(),
            lidCA = cardCInitResModel.cardCInitResTBS.lidCA,
            requestType = RequestType.SIGNATURE,
            language = Language.RUSSIAN,
            thumbs = emptyList()
        )
    }

    private inline fun <reified T> isError(
        json: String, serializer: KSerializer<T>
    ): MessageWrapper<Error<T>>? {
        return try {
            jsonMapper.stringToObject(
                data = json, deserializer = MessageWrapper.serializer(Error.serializer(serializer))
            )
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun checkCardCInitRes(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        cardCInitReqModel: CardCInitReqModel
    ) {
        checkCardCInitResRRPID(
            messageWrapperModel = messageWrapperModel,
            rrpid = cardCInitReqModel.rrpID
        )
        checkCardCInitResThumbs(
            messageWrapperModel = messageWrapperModel,
            thumbs = cardCInitReqModel.thumbs
        )
        checkCardCInitResChallEE(
            messageWrapperModel = messageWrapperModel,
            challEE = cardCInitReqModel.challEE
        )
    }

    private suspend fun checkCardCInitResChallEE(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        challEE: BigInteger
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.challEE != challEE) {
            val errorMessageWrapper = createErrorMessageWrapper(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
                serializer = CardCInitRes.serializer(),
                map = cardCInitResMapper::map
            )
            networkAPI.sendError(
                messageWrapperJson = jsonMapper.objectToString(
                    data = errorMessageWrapper,
                    serializer = MessageWrapper.serializer(Error.serializer(CardCInitRes.serializer()))
                )
            )
            throw ThumbsMismatch()
        }
    }

    private suspend fun checkCardCInitResThumbs(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        thumbs: List<ByteArray>
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.thumbs != thumbs) {
            val errorMessageWrapper = createErrorMessageWrapper(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ThumbsMismatch,
                serializer = CardCInitRes.serializer(),
                map = cardCInitResMapper::map
            )
            networkAPI.sendError(
                messageWrapperJson = jsonMapper.objectToString(
                    data = errorMessageWrapper,
                    serializer = MessageWrapper.serializer(Error.serializer(CardCInitRes.serializer()))
                )
            )
            throw ThumbsMismatch()
        }
    }

    private suspend fun checkCardCInitResRRPID(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        rrpid: BigInteger
    ) {
        if (messageWrapperModel.messageHeaderModel.rrpId == messageWrapperModel.messageModel.cardCInitResTBS.rrpID
            && rrpid == messageWrapperModel.messageModel.cardCInitResTBS.rrpID
        ) {
            val errorMessageWrapper = createErrorMessageWrapper(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.UnknownXID,
                serializer = CardCInitRes.serializer(),
                map = cardCInitResMapper::map
            )
            networkAPI.sendError(
                messageWrapperJson = jsonMapper.objectToString(
                    data = errorMessageWrapper,
                    serializer = MessageWrapper.serializer(Error.serializer(CardCInitRes.serializer()))
                )
            )
            throw UnknownRRPID()
        }
    }

    private suspend fun <T, R> createErrorMessageWrapper(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        serializer: KSerializer<R>,
        map: (T) -> R
    ): MessageWrapper<Error<R>> {
        val keyPair = keyRepository.generatePairKey()
        return errorRepository.createErrorMessageWrapper(
            publicKey = keyPair.public,
            privateKey = keyPair.private,
            messageWrapperModel = messageWrapperModel,
            errorCode = errorCode,
            serializer = serializer,
            map = map
        )
    }

    private fun createCardCInitReq(): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = emptyList()
        )
    }

    private fun createMessageWrapperModel(): MessageWrapperModel<CardCInitReqModel> {
        return createCardCInitReq().run {
            MessageWrapperModel(
                mWExtension = null, messageModel = this, messageHeaderModel = MessageHeaderModel(
                    rrpId = this.rrpID, messageIDModel = MessageIDModel(
                        lIdC = generateNewNumber(),
                        lIdM = generateNewNumber(),
                        xId = generateNewNumber()
                    )
                )
            )
        }
    }

    private suspend fun createMessageWrapperModel(
        cryptoDataModel: CryptoDataModel,
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        rrpid: BigInteger
    ): MessageWrapperModel<CryptoDataModel> {
        return messageWrapperRepository.changeMessage(
            messageModel = cryptoDataModel,
            messageWrapperModel = messageWrapperModel,
            rrpid = rrpid
        )
    }

    private suspend fun checkCardCInitRes(unknownMessageWrapperJson: String): MessageWrapper<CardCInitRes> {
        val errorMessageWrapper =
            isError(json = unknownMessageWrapperJson, serializer = CardCInitReq.serializer())
        if (errorMessageWrapper != null) {
            errorRepository.checkError(
                error = errorMessageWrapper.message,
                map = cardCInitReqMapper::map,
                serializer = ErrorTBS.serializer(CardCInitReq.serializer())
            )
        }
        return jsonMapper.stringToObject(
            data = unknownMessageWrapperJson,
            deserializer = MessageWrapper.serializer(CardCInitRes.serializer())
        )
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}