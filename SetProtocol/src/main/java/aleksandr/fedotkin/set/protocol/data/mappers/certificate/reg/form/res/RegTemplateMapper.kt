package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegTemplate
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel
import kotlinx.serialization.KSerializer

class RegTemplateMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val regFieldMapper: RegFieldMapper
) : SetMapper<RegTemplateModel, RegTemplate> {

    override val serializer: KSerializer<RegTemplate>
        get() = RegTemplate.serializer()

    override fun map(value: RegTemplateModel): RegTemplate {
        return RegTemplate(
            regFormID = bigIntegerMapper.map(value = value.regFormID),
            brandLogoURL = value.brandLogoURL,
            cardLogoURL = value.cardLogoURL,
            regFieldSeq = value.regFieldSeq.map { regFieldMapper.map(value = it) }
        )
    }

    override fun reverseMap(value: RegTemplate): RegTemplateModel {
        return RegTemplateModel(
            regFormID = bigIntegerMapper.reverseMap(value = value.regFormID),
            brandLogoURL = value.brandLogoURL,
            cardLogoURL = value.cardLogoURL,
            regFieldSeq = value.regFieldSeq.map { regFieldMapper.reverseMap(value = it) }
        )
    }
}
