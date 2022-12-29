package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.core.Model

data class IDDataModel(
    val merchantAcquirerID: MerchantAcquirerIDModel,
    val acquirerID: AcquirerIDModel
) : Model
