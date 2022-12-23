package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel

class CertificateUseCase(
    private val cardCInitReqUseCase: CardCInitReqUseCase,
    private val cardCInitResUseCase: CardCInitResUseCase,
    private val regFormReqUseCase: RegFormReqUseCase
) {

    suspend fun getCertificate(number: String) {
        val messageWrapperCardCInitResModel = cardCInit()
        val response = createAndSendRegFormReq(
            number = number,
            messageWrapperModel = messageWrapperCardCInitResModel
        )
    }

    private suspend fun sendCardCInitReq(): Pair<String, CardCInitReqModel> {
        return cardCInitReqUseCase.sendCardCInitReq()
    }

    suspend fun cardCInit(): MessageWrapperModel<CardCInitResModel> {
        return sendCardCInitReq().run {
            cardCInitResUseCase.checkCardCInitRes(
                messageWrapperJson = first,
                challEE = second.challEE,
                rrpid = second.rrpID,
                thumbs = second.thumbs
            )
        }
    }

    suspend fun createAndSendRegFormReq(
        number: String,
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>
    ): String {
        return regFormReqUseCase.createAndSendMessageWrapperJson(
            messageWrapperModel = messageWrapperModel,
            number = number
        )
    }
}
