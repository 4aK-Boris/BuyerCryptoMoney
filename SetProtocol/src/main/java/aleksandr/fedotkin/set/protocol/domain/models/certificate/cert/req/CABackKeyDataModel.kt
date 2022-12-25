package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import javax.crypto.SecretKey

data class CABackKeyDataModel(
    val caaIgId: String,
    val caKey: SecretKey
) : Model
