package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormDataModel
import org.koin.test.inject

class RegFormDataMapperTest : BaseObjectMapperTest<RegFormDataModel, RegFormData>() {

    override val mapper by inject<RegFormDataMapper>()

    override suspend fun generateModel(): RegFormDataModel {
        return RegFormDataModel(
            regTemplate = regTemplateMapperTest.generateModel(),
            policyText = "7x26167351267c536126 b32902 783180m 3921-"
        )
    }

    private val regTemplateMapperTest by inject<RegTemplateMapperTest>()
}