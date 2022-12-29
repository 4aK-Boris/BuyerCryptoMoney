package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReq
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EXHMapperTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import org.koin.test.inject

class RegFormReqMapperTest : BaseObjectMapperTest<RegFormReqModel, RegFormReq>() {

    override val mapper by inject<RegFormReqMapper>()

    override suspend fun generateModel(): RegFormReqModel {
        return RegFormReqModel(exh = exhMapperTest.generateModel())
    }

    private val exhMapperTest by inject<EXHMapperTest>()
}