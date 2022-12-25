package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncModel
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncXModel

data class CertReqModel(
    val encX: EncXModel,
    val enc: EncModel
): Model
