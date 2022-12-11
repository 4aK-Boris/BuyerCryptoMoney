package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitReqRepository

class CardCInitReqUseCase(
    private val cardCInitReqRepository: CardCInitReqRepository,
    private val networkAPI: NetworkAPI
) {

    suspend fun sendCardCInitReq(): Pair<String, CardCInitReqModel> {
        return cardCInitReqRepository.createCardCInitReqMessageWrapperJson().run {
            networkAPI.sendCardCInitReq(
                messageWrapperJson = first
            ) to second
        }
    }
}
