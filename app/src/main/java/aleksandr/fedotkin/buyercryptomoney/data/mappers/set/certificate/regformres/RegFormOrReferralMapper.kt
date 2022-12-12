package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.RegFormOrReferral
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.RegFormOrReferralModel

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
