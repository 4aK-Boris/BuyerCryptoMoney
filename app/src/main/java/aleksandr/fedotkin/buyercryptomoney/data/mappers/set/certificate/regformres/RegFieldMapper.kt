package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.RegField
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.RegFieldModel

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
