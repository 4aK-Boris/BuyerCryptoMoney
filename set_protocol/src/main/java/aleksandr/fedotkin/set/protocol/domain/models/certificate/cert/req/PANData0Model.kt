package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.math.BigInteger
import java.time.LocalDate

data class PANData0Model(
    val pan: BigInteger,
    val cardExpiry: LocalDate,
    val cardSecret: BigInteger,
    val exNonce: BigInteger
) : Model
