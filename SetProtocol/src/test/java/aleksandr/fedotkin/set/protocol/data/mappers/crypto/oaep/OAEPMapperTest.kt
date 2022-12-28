package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEPModel
import org.koin.test.inject

class OAEPMapperTest : BaseObjectMapperTest<OAEPModel, OAEP>() {

    override val mapper by inject<OAEPMapper>()

    override suspend fun generateModel(): OAEPModel {
        return OAEPModel(secretKey = generateSecretKey())
    }
}