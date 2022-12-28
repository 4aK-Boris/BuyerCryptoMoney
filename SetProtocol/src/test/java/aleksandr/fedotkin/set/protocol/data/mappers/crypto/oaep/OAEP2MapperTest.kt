package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP2
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP2Model
import org.koin.test.inject

class OAEP2MapperTest : BaseObjectMapperTest<OAEP2Model, OAEP2>() {

    override val mapper by inject<OAEP2Mapper>()

    override suspend fun generateModel(): OAEP2Model {
        return OAEP2Model(secretKey = generateSecretKey(), hash = generateByteArray(size = 32))
    }
}