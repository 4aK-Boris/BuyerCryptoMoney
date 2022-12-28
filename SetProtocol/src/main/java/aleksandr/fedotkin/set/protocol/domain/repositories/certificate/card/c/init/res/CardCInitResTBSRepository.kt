package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.card.c.init.res.CardCInitResTBS
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import java.math.BigInteger

interface CardCInitResTBSRepository: BaseSetRepository<CardCInitResTBSModel, CardCInitResTBS> {

    suspend fun verifySignature(model: CardCInitResTBSModel, signature: ByteArray): Boolean

    suspend fun create(model: CardCInitReqModel): CardCInitResTBSModel

    suspend fun createSignature(model: CardCInitResTBSModel): ByteArray

    suspend fun checkRRPID(rrpid: BigInteger?, secondRRPID: BigInteger, thirdRRPID: BigInteger): Boolean

    suspend fun checkChallEE(challEE: BigInteger, secondChallEE: BigInteger): Boolean

    suspend fun checkThumbs(thumbs: List<ByteArray>, secondThumbs: List<ByteArray>): Boolean
}