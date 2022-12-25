package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger

data class MerchantAcquirerIDModel(
    val merchantBIN: BigInteger,
    val merchantID: BigInteger,
) : Model
