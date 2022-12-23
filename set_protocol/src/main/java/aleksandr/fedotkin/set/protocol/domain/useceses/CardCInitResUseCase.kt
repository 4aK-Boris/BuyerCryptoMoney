package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.ChallengeMismatch
import aleksandr.fedotkin.set.protocol.core.ThumbsMismatch
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import java.math.BigInteger

class CardCInitResUseCase(
    cardCInitResRepository: CardCInitResRepository,
) : ResponseUseCase<CardCInitResModel, CardCInitRes>() {

    override val serializer = cardCInitResRepository.serializer

    override val convertToDTO = cardCInitResRepository.convertToDTO

    override val convertToModel = cardCInitResRepository.convertToModel

    suspend fun checkCardCInitRes(
        messageWrapperJson: String,
        challEE: BigInteger,
        rrpid: BigInteger,
        thumbs: List<ByteArray>
    ): MessageWrapperModel<CardCInitResModel> {
        return checkMessageWrapperAndConvertToModel(messageWrapperJson = messageWrapperJson).also { messageWrapperModel ->
            checkCardCInitResChallEE(
                messageWrapperModel = messageWrapperModel,
                challEE = challEE
            )
            checkRRPID(
                messageWrapperModel = messageWrapperModel,
                rrpid = rrpid,
                messageRRPID = messageWrapperModel.messageModel.cardCInitResTBS.rrpID
            )
            checkCardCInitResThumbs(
                messageWrapperModel = messageWrapperModel,
                thumbs = thumbs
            )
        }
    }

    private suspend fun checkCardCInitResChallEE(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        challEE: BigInteger
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.challEE != challEE) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
            )
            throw ChallengeMismatch()
        }
    }

    private suspend fun checkCardCInitResThumbs(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        thumbs: List<ByteArray>
    ) {
        if (messageWrapperModel.messageModel.cardCInitResTBS.thumbs != thumbs) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ThumbsMismatch,
            )
            throw ThumbsMismatch()
        }
    }
}
