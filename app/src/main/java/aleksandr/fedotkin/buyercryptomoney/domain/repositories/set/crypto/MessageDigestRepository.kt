package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto

import kotlinx.serialization.KSerializer

interface MessageDigestRepository {

    suspend fun <T> messageDigest(data: T, serializer: KSerializer<T>): ByteArray
    suspend fun <T> verifyMessageDigest(data: T, serializer: KSerializer<T>, array: ByteArray): Boolean
}