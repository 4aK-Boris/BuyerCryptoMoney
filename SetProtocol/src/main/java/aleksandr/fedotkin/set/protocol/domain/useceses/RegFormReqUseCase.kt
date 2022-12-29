package aleksandr.fedotkin.set.protocol.domain.useceses

//class RegFormReqUseCase(
//    private val regFormReqRepository: RegFormReqRepository,
//    private val cryptoDataUseCase: CryptoDataUseCase
//) : RequestUseCase<RegFormReqDataModel, RegFormReqData>() {
//
//    override val serializer = RegFormReqData.serializer()
//
//    override val convertToDTO = regFormReqRepository.convertToDTO
//
//    override val convertToModel = regFormReqRepository.convertToModel
//
//    suspend fun createAndSendMessageWrapper(
//        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
//        number: String
//    ): String {
//        return with(messageWrapperModel.messageModel.cardCInitResTBS) {
//            regFormReqRepository.createCryptoDataModel(
//                number = number,
//                lidEE = lidEE,
//                lidCA = lidCA,
//                caeThumb = caeThumb
//            ).let { (cryptoDataModel, rrpid) ->
//                networkAPI.sendRegFormReq(
//                    messageWrapperJson = cryptoDataUseCase.cryptoDataModelToJson(
//                        messageWrapperModel = changeMessageModel(
//                            messageModel = cryptoDataModel,
//                            messageWrapperModel = messageWrapperModel,
//                            rrpid = rrpid
//                        )
//                    )
//                )
//            }
//        }
//    }
//}
