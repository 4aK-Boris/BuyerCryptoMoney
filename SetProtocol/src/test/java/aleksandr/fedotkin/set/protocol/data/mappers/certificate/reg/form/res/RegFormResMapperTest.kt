package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import org.koin.test.inject

class RegFormResMapperTest: BaseObjectMapperTest<RegFormResModel, RegFormRes>() {

    override val mapper by inject<RegFormResMapper>()

    override suspend fun generateModel(): RegFormResModel {
        return RegFormResModel(
            signature = generateByteArray(size = 32),
            regFormResTBS = regFormResTBSMapperTest.generateModel()
        )
    }

    private val regFormResTBSMapperTest by inject<RegFormResTBSMapperTest>()
}