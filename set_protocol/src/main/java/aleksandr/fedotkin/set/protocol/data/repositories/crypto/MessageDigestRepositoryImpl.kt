package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.MessageDigestRepository
import java.security.MessageDigest
import kotlinx.serialization.KSerializer

class MessageDigestRepositoryImpl(
    private val messageDigest: MessageDigest,
    private val jsonMapper: JsonMapper
) : MessageDigestRepository {

    override suspend fun <T> messageDigest(data: T, serializer: KSerializer<T>): ByteArray {
        return messageDigest(
            data = jsonMapper.objectToByteArray(
                serializer = serializer,
                data = data
            )
        )
    }

    override suspend fun messageDigest(data: ByteArray): ByteArray {
        return messageDigest.digest(data)
    }

    override suspend fun <T> verifyMessageDigest(
        data: T,
        serializer: KSerializer<T>,
        array: ByteArray
    ): Boolean {
        val byteArrayData = jsonMapper.objectToByteArray(serializer = serializer, data = data)
        return byteArrayData.contentEquals(array)
    }
}
