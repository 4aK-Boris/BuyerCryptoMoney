package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import org.koin.test.inject

class RegFormReqDataMapperTest : BaseObjectMapperTest<RegFormReqDataModel, RegFormReqData>() {

    override val mapper by inject<RegFormReqDataMapper>()

    override suspend fun generateModel(): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = generateNewNumber(),
            requestType = RequestType.SIGNATURE,
            lidCA = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE2 = generateNewNumber(),
            language = Language.RUSSIAN,
            thumbs = listOf(generateByteArray(size = 256))
        )
    }
}