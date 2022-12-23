package aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class CardCInitReqModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE: BigInteger,
    val brandID: BigInteger,
    val thumbs: List<ByteArray>
) : Model
