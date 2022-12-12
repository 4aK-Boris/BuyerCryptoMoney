package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

@kotlinx.serialization.Serializable
data class RegFormData(
    val regTemplate: RegTemplate?,
    val policyText: String
)
