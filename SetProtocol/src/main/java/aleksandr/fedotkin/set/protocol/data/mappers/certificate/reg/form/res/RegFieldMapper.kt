package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegField
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import kotlinx.serialization.KSerializer

class RegFieldMapper(
    private val bigIntegerMapper: BigIntegerMapper
) : SetMapper<RegFieldModel, RegField> {

    override val serializer: KSerializer<RegField>
        get() = RegField.serializer()

    override fun map(value: RegFieldModel): RegField {
        return RegField(
            fieldId = value.fieldId?.let { bigIntegerMapper.map(value = it) },
            fieldName = value.fieldName,
            fieldDesc = value.fieldDesc,
            fieldLen = value.fieldLen,
            fieldRequired = value.fieldRequired,
            fieldInvisible = value.fieldInvisible
        )
    }

    override fun reverseMap(value: RegField): RegFieldModel {
        return RegFieldModel(
            fieldId = value.fieldId?.let { bigIntegerMapper.reverseMap(value = it) },
            fieldName = value.fieldName,
            fieldDesc = value.fieldDesc,
            fieldLen = value.fieldLen,
            fieldRequired = value.fieldRequired,
            fieldInvisible = value.fieldInvisible
        )
    }
}
