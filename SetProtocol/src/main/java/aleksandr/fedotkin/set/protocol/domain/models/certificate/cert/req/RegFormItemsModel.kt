package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.Model

data class RegFormItemsModel(
    val fieldName: String,
    val fieldValue: String
) : Model
