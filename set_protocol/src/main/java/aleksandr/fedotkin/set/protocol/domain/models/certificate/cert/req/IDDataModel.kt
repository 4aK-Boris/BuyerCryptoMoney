package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class IDDataModel(
    val merchantAcquirerID: MerchantAcquirerIDModel,
    val acquirerID: AcquirerIDModel
) : Model
