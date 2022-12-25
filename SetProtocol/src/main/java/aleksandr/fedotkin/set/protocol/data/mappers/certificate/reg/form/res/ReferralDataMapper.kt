package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.ReferralData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel

class ReferralDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val regFieldMapper: RegFieldMapper
) {

    fun map(model: ReferralDataModel): ReferralData {
        return ReferralData(
            regFormID = bigIntegerMapper.map(number = model.regFormID),
            brandLogoURL = model.brandLogoURL,
            cardLogoURL = model.cardLogoURL,
            regFieldSeq = model.regFieldSeq.map { regFieldMapper.map(model = it) }
        )
    }

    fun map(dto: ReferralData): ReferralDataModel {
        return ReferralDataModel(
            regFormID = bigIntegerMapper.map(string = dto.regFormID),
            brandLogoURL = dto.brandLogoURL,
            cardLogoURL = dto.cardLogoURL,
            regFieldSeq = dto.regFieldSeq.map { regFieldMapper.map(dto = it) }
        )
    }
}
