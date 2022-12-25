package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.res

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class FailedItemModel(
    val itemNumber: Int,
    val itemReason: String
) : Model
