package aleksandr.fedotkin.set.protocol.domain.useceses.server

import aleksandr.fedotkin.set.protocol.core.usecase.BaseServerResponseUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey

class CardCInitResServerUseCase(
    override val publicKey: PublicKey,
    override val privateKey: PrivateKey,
    override val repository: CardCInitResRepository,
    messageWrapperRepository: MessageWrapperRepository<CardCInitResModel, CardCInitRes>
) : BaseServerResponseUseCase<CardCInitResModel, CardCInitRes>(messageWrapperRepository) {

    override lateinit var rrpid: BigInteger

    override lateinit var messageWrapperModel: MessageWrapperModel<CardCInitResModel>

    override lateinit var sendMessage: suspend (String) -> Unit

    suspend fun createAndSend(messageWrapperModel: MessageWrapperModel<CardCInitReqModel>) {
        convertToMessageWrapperModel(
            messageWrapperModel = messageWrapperModel,
            model = repository.create(cardCInitReqModel = messageWrapperModel.messageModel)
        )
        sendMessage(json)
    }
}
