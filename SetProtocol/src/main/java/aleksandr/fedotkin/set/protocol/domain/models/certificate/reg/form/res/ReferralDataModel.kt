package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class ReferralDataModel(
    val regFormID: BigInteger,
    val brandLogoURL: String?,
    val cardLogoURL: String?,
    val regFieldSeq: List<RegFieldModel>
) : Model
