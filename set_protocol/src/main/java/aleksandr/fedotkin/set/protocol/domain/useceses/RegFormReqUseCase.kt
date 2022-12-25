package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository

class RegFormReqUseCase(
    private val regFormReqRepository: RegFormReqRepository,
    private val cryptoDataUseCase: CryptoDataUseCase
) : RequestUseCase<RegFormReqDataModel, RegFormReqData>() {

    override val serializer = RegFormReqData.serializer()

    override val convertToDTO = regFormReqRepository.convertToDTO

    override val convertToModel = regFormReqRepository.convertToModel

    suspend fun createAndSendMessageWrapper(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): String {
        return with(messageWrapperModel.messageModel.cardCInitResTBS) {
            regFormReqRepository.createCryptoDataModel(
                number = number,
                lidEE = lidEE,
                lidCA = lidCA,
                caeThumb = caeThumb
            ).let { (cryptoDataModel, rrpid) ->
                networkAPI.sendRegFormReq(
                    messageWrapperJson = cryptoDataUseCase.cryptoDataModelToJson(
                        messageWrapperModel = changeMessageModel(
                            messageModel = cryptoDataModel,
                            messageWrapperModel = messageWrapperModel,
                            rrpid = rrpid
                        )
                    )
                )
            }
        }
    }
}
