package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class PANOnlyModel(
    val pan: BigInteger,
    val exNonce: BigInteger
): Model