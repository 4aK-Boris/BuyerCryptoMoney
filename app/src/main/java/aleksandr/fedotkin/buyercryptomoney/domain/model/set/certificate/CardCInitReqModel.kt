package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageModel
import java.math.BigInteger

data class CardCInitReqModel(
    val rrpID: BigInteger,
    val lidEE: BigInteger,
    val challEE: BigInteger,
    val brandID: BigInteger,
    val thumbs: List<ByteArray>
)
