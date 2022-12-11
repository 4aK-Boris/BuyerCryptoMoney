package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import java.security.MessageDigest
import kotlinx.serialization.KSerializer

class MessageDigestRepositoryImpl(
    private val messageDigest: MessageDigest,
    private val jsonMapper: JsonMapper
) : MessageDigestRepository {
    override suspend fun <T> messageDigest(data: T, serializer: KSerializer<T>): ByteArray {
        val byteArrayData = jsonMapper.objectToByteArray(serializer = serializer, data = data)
        return messageDigest.digest(byteArrayData)
    }

    override suspend fun <T> verifyMessageDigest(data: T, serializer: KSerializer<T>, array: ByteArray): Boolean {
        val byteArrayData = jsonMapper.objectToByteArray(serializer = serializer, data = data)
        return byteArrayData.contentEquals(array)
    }
}