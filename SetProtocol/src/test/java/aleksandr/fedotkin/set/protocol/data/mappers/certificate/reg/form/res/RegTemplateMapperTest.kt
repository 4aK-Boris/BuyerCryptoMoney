package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegTemplate
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegTemplateModel
import org.koin.test.inject

class RegTemplateMapperTest : BaseObjectMapperTest<RegTemplateModel, RegTemplate>() {

    override val mapper by inject<RegTemplateMapper>()

    override suspend fun generateModel(): RegTemplateModel {
        return RegTemplateModel(
            regFormID = generateNewNumber(),
            brandLogoURL = null,
            cardLogoURL = null,
            regFieldSeq = listOf(
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel(),
                regFieldMapperTest.generateModel()
            )
        )
    }

    private val regFieldMapperTest by inject<RegFieldMapperTest>()
}