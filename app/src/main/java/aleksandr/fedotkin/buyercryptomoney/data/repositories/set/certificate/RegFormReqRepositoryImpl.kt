package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.RegFormReqData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.PANOnly
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.PANOnlyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqDataRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CryptoDataRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.EXHRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.usecases.set.MessageWrapperUseCase
import java.math.BigInteger
import java.security.PublicKey

class RegFormReqRepositoryImpl(
    private val panOnlyRepository: PANOnlyRepository,
    private val regFormReqDataRepository: RegFormReqDataRepository,
    private val exhRepository: EXHRepository,
    private val keyRepository: KeyRepository,
    private val messageWrapperRepository: MessageWrapperRepository,
    private val cryptoDataRepository: CryptoDataRepository,
    private val messageWrapperUseCase: MessageWrapperUseCase
) : RegFormReqRepository {

    override suspend fun createMessageWrapperJson(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): String {
        return messageWrapperRepository.messageWrapperToJson(
            messageWrapper = createMessageWrapper(
                messageWrapperModel = messageWrapperModel,
                number = number
            ), serializer = CryptoData.serializer()
        )
    }

    override suspend fun createMessageWrapper(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): MessageWrapper<CryptoData> {
        return messageWrapperRepository.convertToDTO(
            messageWrapperModel = createMessageWrapperModel(
                messageWrapperModel = messageWrapperModel,
                number = number
            ), map = cryptoDataRepository::convertToDTO
        )
    }

    override suspend fun createMessageWrapperModel(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): MessageWrapperModel<CryptoDataModel> {
        val publicKey = keyRepository.createCertificate(
            certificate = messageWrapperModel.messageModel.cardCInitResTBS.caeThumb
        ).publicKey
        with(messageWrapperModel.messageModel.cardCInitResTBS) {
            val (cryptoDataModel, rrpid) = createCryptoDataModel(
                number = number,
                lidEE = lidEE,
                lidCA = lidCA,
                publicKey = publicKey
            )
            return messageWrapperUseCase.changeMessageModel(
                messageWrapperModel = messageWrapperModel,
                messageModel = cryptoDataModel,
                rrpid = rrpid
            )
        }
    }

    override suspend fun createCryptoDataModel(
        number: String,
        lidEE: BigInteger,
        lidCA: BigInteger,
        publicKey: PublicKey
    ): Pair<CryptoDataModel, BigInteger> {
        return regFormReqDataRepository.createRegFormReqDataModel(lidEE = lidEE, lidCA = lidCA)
            .run {
                exhRepository.encrypt(
                    publicKey = publicKey,
                    data = this.first,
                    secondaryData = panOnlyRepository.createPANOnlyModel(number = number),
                    map = regFormReqDataRepository::convertToDTO,
                    secondaryMap = panOnlyRepository::convertToDTO,
                    serializer = RegFormReqData.serializer(),
                    secondarySerializer = PANOnly.serializer()
                ) to this.second
            }
    }
}
