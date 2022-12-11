package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CardCInitReqRepository

class CardCInitReqUseCase(
    private val cardCInitReqRepository: CardCInitReqRepository,
    private val networkAPI: NetworkAPI
) {

    suspend fun sendCardCInitReq(): String {
        return networkAPI.sendCardCInitReq(
            messageWrapperJson = cardCInitReqRepository.createCardCInitReqMessageWrapperJson()
        )
    }
}
