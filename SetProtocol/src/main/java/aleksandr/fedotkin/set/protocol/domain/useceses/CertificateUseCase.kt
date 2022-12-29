package aleksandr.fedotkin.set.protocol.domain.useceses

class CertificateUseCase(
    private val cardCInitReqUseCase: CardCInitReqUseCase,
    private val cardCInitResUseCase: CardCInitResUseCase,
    //private val regFormReqUseCase: RegFormReqUseCase
) {

    suspend fun getCertificate(number: String) {
        val (messageWrapperJson, cardCInitReqModel) = cardCInitReqUseCase.createAndSendMessageWrapper()
        val messageWrapperModel = cardCInitResUseCase.checkCardCInitRes(messageWrapperJson = messageWrapperJson, cardCInitReqModel = cardCInitReqModel)
        println(messageWrapperModel)
    }

//    private suspend fun sendCardCInitReq(): Pair<String, CardCInitReqModel> {
//        return cardCInitReqUseCase.createAndSendMessageWrapper()
//    }
//
//    suspend fun cardCInit(): MessageWrapperModel<CardCInitResModel> {
//        return sendCardCInitReq().run {
//            cardCInitResUseCase.checkCardCInitRes(
//                messageWrapperJson = first,
//                challEE = second.challEE,
//                rrpid = second.rrpID,
//                thumbs = second.thumbs
//            )
//        }
//    }
//
//    suspend fun createAndSendRegFormReq(
//        number: String,
//        messageWrapperModel: MessageWrapperModel<CardCInitResModel>
//    ): String {
//        return regFormReqUseCase.createAndSendMessageWrapperJson(
//            messageWrapperModel = messageWrapperModel,
//            number = number
//        )
//    }
}
