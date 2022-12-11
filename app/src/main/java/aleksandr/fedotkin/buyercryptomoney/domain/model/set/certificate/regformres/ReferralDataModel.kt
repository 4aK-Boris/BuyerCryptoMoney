package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres

import java.math.BigInteger

data class ReferralDataModel(
    val regFormID: BigInteger,
    val brandLogoURL: String?,
    val cardLogoURL: String?,
    val regFieldSeq: List<RegFieldModel>
)
