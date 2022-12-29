package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP3Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.serialization.KSerializer

class OAEP3Mapper<T: Model, R: DTO>(
    private val base64Mapper: Base64Mapper,
    private val keyRepository: KeyRepository,
    private val mapper: SetMapper<T, R>
) : SetMapper<OAEP3Model<T>, OAEP3<R>> {

    override val serializer: KSerializer<OAEP3<R>>
        get() = OAEP3.serializer(mapper.serializer)

    override fun map(value: OAEP3Model<T>): OAEP3<R> {
        return OAEP3(
            secretKey = base64Mapper.map(value = value.secretKey.encoded),
            data = mapper.map(value = value.data)
        )
    }

    override fun reverseMap(value: OAEP3<R>): OAEP3Model<T> {
        return OAEP3Model(
            secretKey = keyRepository.decodeSecretKey(
                keyArray = base64Mapper.reverseMap(
                    value = value.secretKey
                )
            ), data = mapper.reverseMap(value = value.data)
        )
    }
}