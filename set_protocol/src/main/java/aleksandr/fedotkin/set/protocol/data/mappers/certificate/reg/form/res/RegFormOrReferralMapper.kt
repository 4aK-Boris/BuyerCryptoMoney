package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormOrReferral
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel

class RegFormOrReferralMapper(
    private val regFormDataMapper: RegFormDataMapper,
    private val referralDataMapper: ReferralDataMapper
) {

    fun map(model: RegFormOrReferralModel): RegFormOrReferral {
        return RegFormOrReferral(
            referralData = referralDataMapper.map(model = model.referralData),
            regFormData = regFormDataMapper.map(model = model.regFormData)
        )
    }

    fun map(dto: RegFormOrReferral): RegFormOrReferralModel {
        return RegFormOrReferralModel(
            referralData = referralDataMapper.map(dto = dto.referralData),
            regFormData = regFormDataMapper.map(dto = dto.regFormData)
        )
    }
}
