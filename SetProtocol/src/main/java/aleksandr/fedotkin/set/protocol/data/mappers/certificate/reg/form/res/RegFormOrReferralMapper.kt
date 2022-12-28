package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormOrReferral
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel
import kotlinx.serialization.KSerializer

class RegFormOrReferralMapper(
    private val regFormDataMapper: RegFormDataMapper,
    private val referralDataMapper: ReferralDataMapper
) : SetMapper<RegFormOrReferralModel, RegFormOrReferral> {

    override val serializer: KSerializer<RegFormOrReferral>
        get() = RegFormOrReferral.serializer()

    override fun map(value: RegFormOrReferralModel): RegFormOrReferral {
        return RegFormOrReferral(
            referralData = referralDataMapper.map(value = value.referralData),
            regFormData = regFormDataMapper.map(value = value.regFormData)
        )
    }

    override fun reverseMap(value: RegFormOrReferral): RegFormOrReferralModel {
        return RegFormOrReferralModel(
            referralData = referralDataMapper.reverseMap(value = value.referralData),
            regFormData = regFormDataMapper.reverseMap(value = value.regFormData)
        )
    }
}
