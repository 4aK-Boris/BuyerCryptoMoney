package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.exception.SetExternalException
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import java.math.BigInteger

class CardCInitResRepositoryImpl(
    override val mapper: CardCInitResMapper,
    private val cardCInitResTBSRepository: CardCInitResTBSRepository
) : CardCInitResRepository {

    override suspend fun check(
        rrpid: BigInteger?,
        cardCInitReqModel: CardCInitReqModel,
        cardCInitResModel: CardCInitResModel
    ) {
        checkSignature(cardCInitResModel = cardCInitResModel)
        checkRRPID(rrpid, cardCInitReqModel, cardCInitResModel)
        checkChallEE(cardCInitReqModel, cardCInitResModel)
        checkThumbs(cardCInitReqModel, cardCInitResModel)
    }

    override suspend fun create(cardCInitReqModel: CardCInitReqModel): CardCInitResModel {
        val cardCInitResTBSModel = cardCInitResTBSRepository.create(model = cardCInitReqModel)
        val signature = cardCInitResTBSRepository.createSignature(model = cardCInitResTBSModel)
        return CardCInitResModel(signature = signature, cardCInitResTBS = cardCInitResTBSModel)
    }

    private suspend fun checkSignature(cardCInitResModel: CardCInitResModel) {
        if (!cardCInitResTBSRepository.verifySignature(
                signature = cardCInitResModel.signature,
                model = cardCInitResModel.cardCInitResTBS
            )
        ) {
            throw SetExternalException(errorCode = ErrorCode.SignatureFailure)
        }
    }

    private suspend fun checkRRPID(
        rrpid: BigInteger?,
        cardCInitReqModel: CardCInitReqModel,
        cardCInitResModel: CardCInitResModel
    ) {
        if (!cardCInitResTBSRepository.checkRRPID(
                rrpid = rrpid,
                secondRRPID = cardCInitReqModel.rrpID,
                thirdRRPID = cardCInitResModel.cardCInitResTBS.rrpID
            )
        ) {
            throw SetExternalException(errorCode = ErrorCode.UnknownXID)
        }
    }

    private suspend fun checkChallEE(
        cardCInitReqModel: CardCInitReqModel,
        cardCInitResModel: CardCInitResModel
    ) {
        if (!cardCInitResTBSRepository.checkChallEE(
                challEE = cardCInitReqModel.challEE,
                secondChallEE = cardCInitResModel.cardCInitResTBS.challEE
            )
        ) {
            throw SetExternalException(errorCode = ErrorCode.UnknownXID)
        }
    }

    private suspend fun checkThumbs(
        cardCInitReqModel: CardCInitReqModel,
        cardCInitResModel: CardCInitResModel
    ) {
        if (!cardCInitResTBSRepository.checkThumbs(
                thumbs = cardCInitReqModel.thumbs,
                secondThumbs = cardCInitResModel.cardCInitResTBS.thumbs
            )
        ) {
            throw SetExternalException(errorCode = ErrorCode.UnknownXID)
        }
    }
}
