package aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.DTO

@kotlinx.serialization.Serializable
data class RegField(
    val fieldId: String?,
    val fieldName: List<String>,
    val fieldDesc: String?,
    val fieldLen: Int?,
    val fieldRequired: Boolean,
    val fieldInvisible: Boolean
): DTO
