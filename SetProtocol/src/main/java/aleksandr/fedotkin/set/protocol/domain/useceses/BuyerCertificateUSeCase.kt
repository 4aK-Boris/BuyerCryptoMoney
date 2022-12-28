package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitReqClientUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitResClientUseCase

class BuyerCertificateUSeCase(
    private val cardCInitReqUseCase: CardCInitReqClientUseCase,
    private val cardCInitResUseCase: CardCInitResClientUseCase
) {

    suspend fun getCertificate(number: String) {
        val json = cardCInitReqUseCase.createAndSend()
        val cardCInitResTBSModel = cardCInitResUseCase.processing(
            json = json,
            cardCInitReqModel = cardCInitReqUseCase.messageWrapperModel.messageModel
        )
       println(cardCInitResTBSModel)
    }
}