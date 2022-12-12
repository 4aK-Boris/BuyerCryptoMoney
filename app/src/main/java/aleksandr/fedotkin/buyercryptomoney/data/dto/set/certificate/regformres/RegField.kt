package aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres

@kotlinx.serialization.Serializable
data class RegField(
    val fieldId: String?,
    val fieldName: List<String>,
    val fieldDesc: String?,
    val fieldLen: Int?,
    val fieldRequired: Boolean,
    val fieldInvisible: Boolean
)
