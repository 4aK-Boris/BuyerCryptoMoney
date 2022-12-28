package aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP4
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP4Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.serialization.KSerializer

class OAEP4Mapper<T : Model, R : DTO>(
    private val base64Mapper: Base64Mapper,
    private val keyRepository: KeyRepository,
    private val mapper: SetMapper<T, R>
) : SetMapper<OAEP4Model<T>, OAEP4<R>> {

    override val serializer: KSerializer<OAEP4<R>>
        get() = OAEP4.serializer(mapper.serializer)

    override fun map(value: OAEP4Model<T>): OAEP4<R> {
        return OAEP4(
            secretKey = base64Mapper.map(value = value.secretKey.encoded),
            hash = base64Mapper.map(value = value.hash),
            data = mapper.map(value = value.data)
        )
    }

    override fun reverseMap(value: OAEP4<R>): OAEP4Model<T> {
        return OAEP4Model(
            secretKey = keyRepository.decodeSecretKey(
                keyArray = base64Mapper.reverseMap(
                    value = value.secretKey
                )
            ),
            hash = base64Mapper.reverseMap(value = value.hash),
            data = mapper.reverseMap(value = value.data)
        )
    }
}