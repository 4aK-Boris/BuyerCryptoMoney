package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EXH
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EXHModel
import org.koin.test.inject

class EXHMapperTest : BaseObjectMapperTest<EXHModel, EXH>() {

    override val mapper by inject<EXHMapper>()

    override suspend fun generateModel(): EXHModel {
        return EXHModel(
            data = generateByteArray(size = 1024),
            secretKey = generateByteArray(size = 32)
        )
    }
}