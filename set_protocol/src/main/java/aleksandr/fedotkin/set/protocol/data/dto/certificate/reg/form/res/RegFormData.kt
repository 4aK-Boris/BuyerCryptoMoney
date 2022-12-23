package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class RegFormData(
    val regTemplate: RegTemplate?,
    val policyText: String
): DTO
