package aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto

import java.math.BigInteger

data class PANOnlyModel(
    val pan: BigInteger,
    val exNonce: BigInteger
)