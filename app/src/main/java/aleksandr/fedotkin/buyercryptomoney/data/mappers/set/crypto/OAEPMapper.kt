package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.OAEP3
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.OAEP3Model
import kotlinx.serialization.KSerializer

class OAEPMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val jsonMapper: JsonMapper
) {

    fun <T, R> map(model: OAEP3Model<T>, map: (T) -> R, serializer: KSerializer<R>): OAEP3 {
        return OAEP3(
            key = byteArrayMapper.map(byteArray = model.key.encoded),
            hash = byteArrayMapper.map(byteArray = model.hash),
            p = jsonMapper.objectToString(data = map(model.p), serializer = serializer)
        )
    }
}