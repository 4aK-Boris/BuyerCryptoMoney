package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.RegFormReqRepository

class RegFormReqUseCase(
    private val regFormReqRepository: RegFormReqRepository,
    private val networkAPI: NetworkAPI
) {

    suspend fun createAndSendMessageWrapperJson(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): String {
        return networkAPI.sendRegFormReq(
            messageWrapperJson = createMessageWrapperJson(
                messageWrapperModel = messageWrapperModel,
                number = number
            )
        )
    }

    suspend fun createMessageWrapperJson(
        messageWrapperModel: MessageWrapperModel<CardCInitResModel>,
        number: String
    ): String {
        return regFormReqRepository.createMessageWrapperJson(
            messageWrapperModel = messageWrapperModel,
            number = number
        )
    }
}
