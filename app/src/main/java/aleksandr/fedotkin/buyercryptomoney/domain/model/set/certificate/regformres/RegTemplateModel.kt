package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres

import java.math.BigInteger

data class RegTemplateModel(
    val regFormID: BigInteger,
    val brandLogoURL: String?,
    val cardLogoURL: String?,
    val regFieldSeq: List<RegFieldModel>
)
