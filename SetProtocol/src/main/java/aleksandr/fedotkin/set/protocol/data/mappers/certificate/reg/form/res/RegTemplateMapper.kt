package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegTemplate
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel

class RegTemplateMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val regFieldMapper: RegFieldMapper
) {

    @JvmName("map_model_notnull")
    fun map(model: RegTemplateModel): RegTemplate {
        return RegTemplate(
            regFormID = bigIntegerMapper.map(number = model.regFormID),
            brandLogoURL = model.brandLogoURL,
            cardLogoURL = model.cardLogoURL,
            regFieldSeq = model.regFieldSeq.map { regFieldMapper.map(model = it) }
        )
    }

    @JvmName("map_dto_notnull")
    fun map(dto: RegTemplate): RegTemplateModel {
        return RegTemplateModel(
            regFormID = bigIntegerMapper.map(string = dto.regFormID),
            brandLogoURL = dto.brandLogoURL,
            cardLogoURL = dto.cardLogoURL,
            regFieldSeq = dto.regFieldSeq.map { regFieldMapper.map(dto = it) }
        )
    }

    @JvmName("map_model_nullable")
    fun map(model: RegTemplateModel?): RegTemplate? {
        return model?.let { map(model = it) }
    }

    @JvmName("map_dto_nullable")
    fun map(dto: RegTemplate?): RegTemplateModel? {
        return dto?.let { map(dto = it) }
    }
}
