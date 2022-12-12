package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.ReferralData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.ReferralDataModel

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
