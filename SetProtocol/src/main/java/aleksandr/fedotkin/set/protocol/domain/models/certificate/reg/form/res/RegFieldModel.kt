package aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.Model
import java.math.BigInteger

data class RegFieldModel(
    val fieldId: BigInteger?,
    val fieldName: List<String>,
    val fieldDesc: String?,
    val fieldLen: Int?,
    val fieldRequired: Boolean,
    val fieldInvisible: Boolean
): Model
