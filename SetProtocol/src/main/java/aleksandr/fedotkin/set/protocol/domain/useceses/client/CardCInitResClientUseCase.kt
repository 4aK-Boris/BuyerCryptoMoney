package aleksandr.fedotkin.set.protocol.domain.useceses.client

import aleksandr.fedotkin.set.protocol.core.usecase.BaseClientResponseUseCase
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey

class CardCInitResClientUseCase(
    override val publicKey: PublicKey,
    override val privateKey: PrivateKey,
    override val repository: CardCInitResRepository,
    messageWrapperRepository: MessageWrapperRepository<CardCInitResModel, CardCInitRes>
) : BaseClientResponseUseCase<CardCInitResModel, CardCInitRes>(messageWrapperRepository) {

    override lateinit var messageWrapperModel: MessageWrapperModel<CardCInitResModel>

    override lateinit var rrpid: BigInteger

    suspend fun processing(
        json: String,
        cardCInitReqModel: CardCInitReqModel
    ): MessageWrapperModel<CardCInitResTBSModel> {
        messageWrapperModel = convertFromString(json = json)
        rrpid = messageWrapperModel.messageModel.cardCInitResTBS.rrpID
        repository.check(
            rrpid = messageWrapperModel.messageHeaderModel.rrpId,
            cardCInitResModel = messageWrapperModel.messageModel,
            cardCInitReqModel = cardCInitReqModel
        )
        return convertToMessageWrapperModelS(messageWrapperModel.messageModel.cardCInitResTBS)
    }
}
