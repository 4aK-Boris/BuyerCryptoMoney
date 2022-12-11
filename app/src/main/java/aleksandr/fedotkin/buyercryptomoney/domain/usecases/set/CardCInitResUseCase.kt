package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitRes
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseException
import aleksandr.fedotkin.buyercryptomoney.domain.common.ChallengeMismatch
import aleksandr.fedotkin.buyercryptomoney.domain.common.ThumbsMismatch
import aleksandr.fedotkin.buyercryptomoney.domain.common.UnknownRRPID
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitResRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey

class CardCInitResUseCase(
    private val messageWrapperUseCase: MessageWrapperUseCase,
    private val cardCInitResRepository: CardCInitResRepository,
    private val errorUseCase: ErrorUseCase,
    private val keyRepository: KeyRepository
) {

    suspend fun checkCardCInitRes(
        messageWrapperJson: String,
        challEE: BigInteger,
        rrpid: BigInteger,
        thumbs: List<ByteArray>
    ): MessageWrapperModel<CardCInitResModel> {
        val messageWrapperCardCInitResModel =
            convertJsonToCardCInitResModel(messageWrapperJson = messageWrapperJson)
        checkCardCInitResChallEE(
            messageWrapperModel = messageWrapperCardCInitResModel,
            challEE = challEE
        )
        checkCardCInitResRRPID(messageWrapperModel = messageWrapperCardCInitResModel, rrpid = rrpid)
        checkCardCInitResThumbs(
            messageWrapperModel = messageWrapperCardCInitResModel,
            thumbs = thumbs
        )
        return messageWrapperCardCInitResModel
    }

    private suspend fun convertJsonToCardCInitResModel(messageWrapperJson: String)
            : MessageWrapperModel<CardCInitResModel> {
        return messageWrapperUseCase.checkMessageWrapperAndConvertToModel(
            messageWrapperJson = messageWrapperJson,
            serializer = CardCInitRes.serializer(),
            map = cardCInitResRepository::convertToModel
        )
    }

    private suspend fun checkCardCInitResChallEE(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        challEE: BigInteger
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.challEE != challEE) {
            sendErrorCardCInitRes(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
                exception = ChallengeMismatch()
            )
        }
    }

    private suspend fun checkCardCInitResThumbs(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        thumbs: List<ByteArray>
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.thumbs != thumbs) {
            sendErrorCardCInitRes(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ThumbsMismatch,
                exception = ThumbsMismatch()
            )
        }
    }

    private suspend fun checkCardCInitResRRPID(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        rrpid: BigInteger
    ) {
        if (messageWrapperModel.messageHeaderModel.rrpId == messageWrapperModel.messageModel.cardCInitResTBS.rrpID
            && rrpid == messageWrapperModel.messageModel.cardCInitResTBS.rrpID
        ) {
            sendErrorCardCInitRes(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.UnknownXID,
                exception = UnknownRRPID()
            )
        }
    }

    private suspend fun sendErrorCardCInitRes(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        errorCode: ErrorCode,
        exception: BaseException
    ) {
        val (publicKey, privateKey) = generateKeyPair()
        errorUseCase.sendError(
            messageWrapperModel = messageWrapperModel,
            errorCode = errorCode,
            privateKey = privateKey,
            publicKey = publicKey,
            map = cardCInitResRepository::convertToDTO,
            serializer = CardCInitRes.serializer()
        )
        throw exception
    }

    private suspend fun generateKeyPair(): Pair<PublicKey, PrivateKey> {
        return keyRepository.generatePairKey().run { public to private }
    }
}
