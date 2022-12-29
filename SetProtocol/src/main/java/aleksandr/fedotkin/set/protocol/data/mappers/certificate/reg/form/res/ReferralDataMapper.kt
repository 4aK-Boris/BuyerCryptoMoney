package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.ReferralData
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel
import kotlinx.serialization.KSerializer

class ReferralDataMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val regFieldMapper: RegFieldMapper
) : SetMapper<ReferralDataModel, ReferralData> {

    override val serializer: KSerializer<ReferralData>
        get() = ReferralData.serializer()

    override fun map(value: ReferralDataModel): ReferralData {
        return ReferralData(
            regFormID = bigIntegerMapper.map(value = value.regFormID),
            brandLogoURL = value.brandLogoURL,
            cardLogoURL = value.cardLogoURL,
            regFieldSeq = value.regFieldSeq.map { regFieldMapper.map(value = it) }
        )
    }

    override fun reverseMap(value: ReferralData): ReferralDataModel {
        return ReferralDataModel(
            regFormID = bigIntegerMapper.reverseMap(value = value.regFormID),
            brandLogoURL = value.brandLogoURL,
            cardLogoURL = value.cardLogoURL,
            regFieldSeq = value.regFieldSeq.map { regFieldMapper.reverseMap(value = it) }
        )
    }
}
