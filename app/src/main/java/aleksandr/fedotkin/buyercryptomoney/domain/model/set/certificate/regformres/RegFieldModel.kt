package aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres

import java.math.BigInteger

data class RegFieldModel(
    val fieldId: BigInteger?,
    val fieldName: List<String>,
    val fieldDesc: String?,
    val fieldLen: Int?,
    val fieldRequired: Boolean,
    val fieldInvisible: Boolean
)
