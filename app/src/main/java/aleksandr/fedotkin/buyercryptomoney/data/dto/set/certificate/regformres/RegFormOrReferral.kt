package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

@kotlinx.serialization.Serializable
data class RegFormOrReferral(
    val regFormData: RegFormData,
    val referralData: ReferralData
)
