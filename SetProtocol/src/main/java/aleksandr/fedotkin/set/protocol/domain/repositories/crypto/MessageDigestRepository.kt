package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import kotlinx.serialization.KSerializer

interface MessageDigestRepository {

    suspend fun <T> messageDigest(data: T, serializer: KSerializer<T>): ByteArray

    suspend fun messageDigest(data: ByteArray): ByteArray

    suspend fun <T> verifyMessageDigest(data: T, serializer: KSerializer<T>, array: ByteArray): Boolean
}