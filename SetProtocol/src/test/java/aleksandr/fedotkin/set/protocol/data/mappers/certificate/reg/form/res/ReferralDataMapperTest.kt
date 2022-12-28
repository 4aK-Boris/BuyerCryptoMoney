package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.ReferralData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.ReferralDataModel
import org.koin.test.inject

class ReferralDataMapperTest : BaseObjectMapperTest<ReferralDataModel, ReferralData>() {

    override val mapper by inject<ReferralDataMapper>()

    override suspend fun generateModel(): ReferralDataModel {
        return ReferralDataModel(
            regFormID = generateNewNumber(),
            brandLogoURL = null,
            cardLogoURL = null,
            regFieldSeq = listOf(
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel()
            )
        )
    }

    private val regFieldMapperTest by inject<RegFieldMapperTest>()
}