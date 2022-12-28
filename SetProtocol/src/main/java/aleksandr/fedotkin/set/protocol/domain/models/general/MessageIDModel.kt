package aleksandr.fedotkin.set.protocol.domain.models.general

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class MessageIDModel(
    val lIdC: BigInteger?,
    val lIdM: BigInteger?,
    val xId: BigInteger?
): Model