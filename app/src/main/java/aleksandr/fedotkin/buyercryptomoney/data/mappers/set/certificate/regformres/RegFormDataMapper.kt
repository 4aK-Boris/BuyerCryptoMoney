package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.RegFormData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.RegFormDataModel

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
