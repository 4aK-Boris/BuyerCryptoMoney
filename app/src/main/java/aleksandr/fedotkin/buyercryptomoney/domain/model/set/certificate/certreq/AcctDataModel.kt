package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq

import java.math.BigInteger

data class AcctDataModel(
    val acctIdentification: BigInteger,
    val exNonce: BigInteger
)
