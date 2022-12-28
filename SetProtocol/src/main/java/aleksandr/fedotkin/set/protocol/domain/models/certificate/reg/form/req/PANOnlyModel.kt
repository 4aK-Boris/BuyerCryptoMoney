package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class PANOnlyModel(
    val pan: BigInteger,
    val exNonce: BigInteger
): Model