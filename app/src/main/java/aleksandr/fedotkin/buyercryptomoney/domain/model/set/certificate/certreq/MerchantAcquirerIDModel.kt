package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq

import java.math.BigInteger

data class MerchantAcquirerIDModel(
    val merchantBIN: BigInteger,
    val merchantID: BigInteger,
)
