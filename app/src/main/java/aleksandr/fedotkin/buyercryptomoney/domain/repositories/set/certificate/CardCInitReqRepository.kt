package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.CardCInitReq
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel

interface CardCInitReqRepository {

    fun convertToDTO(cardCInitReqModel: CardCInitReqModel): CardCInitReq

    fun convertToModel(cardCInitReq: CardCInitReq): CardCInitReqModel

    suspend fun createCardCInitReqMessageWrapper(): MessageWrapper<CardCInitReq>

    suspend fun createCardCInitReqMessageWrapperJson(): String
}
