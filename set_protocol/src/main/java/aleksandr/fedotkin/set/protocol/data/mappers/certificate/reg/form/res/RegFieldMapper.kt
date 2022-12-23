package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegField
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel

class RegFieldMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: RegFieldModel): RegField {
        return RegField(
            fieldId = bigIntegerMapper.map(number = model.fieldId),
            fieldName = model.fieldName,
            fieldDesc = model.fieldDesc,
            fieldLen = model.fieldLen,
            fieldRequired = model.fieldRequired,
            fieldInvisible = model.fieldInvisible
        )
    }

    fun map(dto: RegField): RegFieldModel {
        return RegFieldModel(
            fieldId = bigIntegerMapper.map(string = dto.fieldId),
            fieldName = dto.fieldName,
            fieldDesc = dto.fieldDesc,
            fieldLen = dto.fieldLen,
            fieldRequired = dto.fieldRequired,
            fieldInvisible = dto.fieldInvisible
        )
    }
}
