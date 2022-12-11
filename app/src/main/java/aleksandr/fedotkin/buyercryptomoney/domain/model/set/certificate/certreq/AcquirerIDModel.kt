package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq

import java.math.BigInteger

data class AcquirerIDModel(
    val acquirerBIN: BigInteger,
    val acquirerBusinessID: BigInteger?
)
