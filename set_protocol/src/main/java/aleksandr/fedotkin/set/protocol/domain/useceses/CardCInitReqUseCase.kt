package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.req.CardCInitReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository

class CardCInitReqUseCase(
    private val cardCInitReqRepository: CardCInitReqRepository
) : RequestUseCase<CardCInitReqModel, CardCInitReq>() {

    override val serializer = cardCInitReqRepository.serializer

    override val convertToDTO = cardCInitReqRepository.convertToDTO

    override val convertToModel = cardCInitReqRepository.convertToModel

    suspend fun sendCardCInitReq(): Pair<String, CardCInitReqModel> {
        return cardCInitReqRepository.createCardCInitReqModel()
            .let { messageModel ->
                networkAPI.sendCardCInitReq(
                    messageWrapperJson = messageWrapperToJson(
                        messageWrapper = convertToDTO(
                            messageWrapperModel = createMessageWrapperModel(
                                rrpid = messageModel.rrpID,
                                messageModel = messageModel
                            )
                        )
                    )
                ) to messageModel
            }
    }
}
