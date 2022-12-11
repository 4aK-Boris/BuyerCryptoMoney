package aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto

import java.math.BigInteger

@kotlinx.serialization.Serializable
data class PANOnly(
    val pan: String,
    val exNonce: String
)