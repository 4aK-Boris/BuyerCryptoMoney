package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class AcquirerIDModel(
    val acquirerBIN: BigInteger,
    val acquirerBusinessID: BigInteger?
) : Model
