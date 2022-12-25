package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class RegTemplate(
    val regFormID: String,
    val brandLogoURL: String?,
    val cardLogoURL: String?,
    val regFieldSeq: List<RegField>
): DTO
