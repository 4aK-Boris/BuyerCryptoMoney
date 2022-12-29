package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.PANOnly
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import org.koin.test.inject

class PANOnlyMapperTest: BaseObjectMapperTest<PANOnlyModel, PANOnly>() {

    override val mapper by inject<PANOnlyMapper>()

    override suspend fun generateModel(): PANOnlyModel {
        return PANOnlyModel(
            pan = generateNewNumber(),
            exNonce = generateNewNumber()
        )
    }
}