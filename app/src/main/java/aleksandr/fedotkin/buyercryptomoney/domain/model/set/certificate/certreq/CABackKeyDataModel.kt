package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq

import javax.crypto.SecretKey

data class CABackKeyDataModel(
    val caaIgId: String,
    val caKey: SecretKey
)
