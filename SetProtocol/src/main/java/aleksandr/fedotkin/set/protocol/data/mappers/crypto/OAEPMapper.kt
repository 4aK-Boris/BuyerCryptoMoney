package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP2
import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP3
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP2Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP3Model
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import kotlinx.serialization.KSerializer

class OAEPMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val jsonMapper: JsonMapper,
    private val keyRepository: KeyRepository
) {

    fun <T: Model, R: DTO> map(model: OAEP3Model<T>, map: (T) -> R, serializer: KSerializer<R>): OAEP3 {
        return OAEP3(
            secretKey = byteArrayMapper.map(byteArray = model.secretKey.encoded),
            hash = byteArrayMapper.map(byteArray = model.hash),
            p = jsonMapper.objectToString(data = map(model.p), serializer = serializer)
        )
    }

    fun <T: Model, R: DTO> map(dto: OAEP3, map: (R) -> T, serializer: KSerializer<R>): OAEP3Model<T> {
        return OAEP3Model(
            secretKey = keyRepository.decodeSecretKey(keyArray = byteArrayMapper.map(string = dto.secretKey)),
            hash = byteArrayMapper.map(string = dto.hash),
            p = map(jsonMapper.stringToObject(data = dto.p, deserializer = serializer))
        )
    }

    fun <T: Model, R: DTO> map(model: OAEP2Model<T>, map: (T) -> R, serializer: KSerializer<R>): OAEP2 {
        return OAEP2(
            secretKey = byteArrayMapper.map(byteArray = model.secretKey.encoded),
            p = jsonMapper.objectToString(data = map(model.p), serializer = serializer)
        )
    }

    fun <T: Model, R: DTO> map(dto: OAEP2, map: (R) -> T, serializer: KSerializer<R>): OAEP2Model<T> {
        return OAEP2Model(
            secretKey = keyRepository.decodeSecretKey(keyArray = byteArrayMapper.map(string = dto.secretKey)),
            p = map(jsonMapper.stringToObject(data = dto.p, deserializer = serializer))
        )
    }
}
