package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel
import kotlinx.serialization.KSerializer

class RegFormDataMapper(
    private val regTemplateMapper: RegTemplateMapper
) : SetMapper<RegFormDataModel, RegFormData> {

    override val serializer: KSerializer<RegFormData>
        get() = RegFormData.serializer()

    override fun map(value: RegFormDataModel): RegFormData {
        return RegFormData(
            regTemplate = value.regTemplate?.let { regTemplateMapper.map(value = it) },
            policyText = value.policyText
        )
    }

    override fun reverseMap(value: RegFormData): RegFormDataModel {
        return RegFormDataModel(
            regTemplate = value.regTemplate?.let { regTemplateMapper.reverseMap(value = it) },
            policyText = value.policyText
        )
    }
}
