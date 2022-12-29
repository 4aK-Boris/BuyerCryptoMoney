package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.Model

data class RegFormDataModel(
    val regTemplate: RegTemplateModel?,
    val policyText: String
): Model
