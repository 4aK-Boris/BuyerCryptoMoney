package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

@kotlinx.serialization.Serializable
data class RegTemplate(
    val regFormID: String,
    val brandLogoURL: String?,
    val cardLogoURL: String?,
    val regFieldSeq: List<RegField>
)
