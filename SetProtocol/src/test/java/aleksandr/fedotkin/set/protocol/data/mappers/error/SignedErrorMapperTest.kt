package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.error.SignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel
import org.koin.test.inject

class SignedErrorMapperTest : BaseObjectMapperTest<SignedErrorModel, SignedError>() {

    override val mapper by inject<SignedErrorMapper>()

    override suspend fun generateModel(): SignedErrorModel {
        return SignedErrorModel(signature = generateByteArray(size = 32))
    }
}