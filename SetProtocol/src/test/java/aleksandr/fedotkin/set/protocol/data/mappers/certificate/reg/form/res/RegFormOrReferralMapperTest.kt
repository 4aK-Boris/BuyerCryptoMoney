package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormOrReferral
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormOrReferralModel
import org.koin.test.inject

class RegFormOrReferralMapperTest :
    BaseObjectMapperTest<RegFormOrReferralModel, RegFormOrReferral>() {

    override val mapper by inject<RegFormOrReferralMapper>()

    override suspend fun generateModel(): RegFormOrReferralModel {
        return RegFormOrReferralModel(
            regFormData = regFormDataMapperTest.generateModel(),
            referralData = referralDataMapperTest.generateModel()
        )
    }

    private val regFormDataMapperTest by inject<RegFormDataMapperTest>()
    private val referralDataMapperTest by inject<ReferralDataMapperTest>()
}