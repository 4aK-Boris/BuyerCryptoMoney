package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.DTO

@kotlinx.serialization.Serializable
data class RegFormOrReferral(
    val regFormData: RegFormData,
    val referralData: ReferralData
): DTO
