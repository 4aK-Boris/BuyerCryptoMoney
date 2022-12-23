package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel

class RegFormDataMapper(
    private val regTemplateMapper: RegTemplateMapper
) {

    fun map(model: RegFormDataModel): RegFormData {
        return RegFormData(
            regTemplate = regTemplateMapper.map(model = model.regTemplate),
            policyText = model.policyText
        )
    }

    fun map(dto: RegFormData): RegFormDataModel {
        return RegFormDataModel(
            regTemplate = regTemplateMapper.map(dto = dto.regTemplate),
            policyText = dto.policyText
        )
    }
}
