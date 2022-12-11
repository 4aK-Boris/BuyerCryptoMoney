package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq

import java.math.BigInteger
import java.time.LocalDate

data class PANData0Model(
    val pan: BigInteger,
    val cardExpiry: LocalDate,
    val cardSecret: BigInteger,
    val exNonce: BigInteger
)
