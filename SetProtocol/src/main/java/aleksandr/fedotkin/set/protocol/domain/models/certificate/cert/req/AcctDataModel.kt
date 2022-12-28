package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class AcctDataModel(
    val acctIdentification: BigInteger,
    val exNonce: BigInteger
) : Model
