package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import java.security.Key
import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.serialization.KSerializer

interface CipherRepository {

    suspend fun <T> asymmetricEncrypt(data: T, serializer: KSerializer<T>, publicKey: PublicKey): ByteArray

    suspend fun <T> asymmetricDecrypt(data: ByteArray, deserializer: KSerializer<T>, privateKey: PrivateKey): T

    suspend fun <T> symmetricDecrypt(data: ByteArray, deserializer: KSerializer<T>, key: Key): T

    suspend fun <T> symmetricEncrypt(data: T, serializer: KSerializer<T>, key: Key): ByteArray

    suspend fun symmetricEncrypt(data: ByteArray, key: Key): ByteArray

    suspend fun symmetricDecrypt(data: ByteArray, key: Key): ByteArray
}