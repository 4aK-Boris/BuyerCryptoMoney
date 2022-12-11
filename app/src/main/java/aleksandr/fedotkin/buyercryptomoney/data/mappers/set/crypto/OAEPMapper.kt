package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.OAEP3
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.OAEP3Model
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import kotlinx.serialization.KSerializer

class OAEPMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val jsonMapper: JsonMapper,
    private val keyRepository: KeyRepository
) {

    fun <T, R> map(model: OAEP3Model<T>, map: (T) -> R, serializer: KSerializer<R>): OAEP3 {
        return OAEP3(
            secretKey = byteArrayMapper.map(byteArray = model.secretKey.encoded),
            hash = byteArrayMapper.map(byteArray = model.hash),
            p = jsonMapper.objectToString(data = map(model.p), serializer = serializer)
        )
    }

    fun <T, R> map(dto: OAEP3, map: (T) -> R, serializer: KSerializer<T>): OAEP3Model<R> {
        return OAEP3Model(
            secretKey = keyRepository.decodeSecretKey(keyArray = byteArrayMapper.map(string = dto.secretKey)),
            hash = byteArrayMapper.map(string = dto.hash),
            p = map(jsonMapper.stringToObject(data = dto.p, deserializer = serializer))
        )
    }
}
