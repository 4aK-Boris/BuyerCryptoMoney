package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate

import java.math.BigInteger

data class CardCInitReqModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE: BigInteger,
    val brandID: BigInteger,
    val thumbs: List<ByteArray>
)
