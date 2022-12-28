package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.Model

data class RegFormOrReferralModel(
    val regFormData: RegFormDataModel,
    val referralData: ReferralDataModel
): Model
