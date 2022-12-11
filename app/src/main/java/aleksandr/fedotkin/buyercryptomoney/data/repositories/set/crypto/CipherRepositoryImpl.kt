package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CipherRepository
import java.security.Key
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import kotlinx.serialization.KSerializer

class CipherRepositoryImpl(
    private val asymmetricCipher: Cipher,
    private val symmetricCipher: Cipher,
    private val jsonMapper: JsonMapper
): CipherRepository {
    override suspend fun <T> asymmetricEncrypt(data: T, serializer: KSerializer<T>, publicKey: PublicKey): ByteArray {
        asymmetricCipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val array = jsonMapper.objectToByteArray(data = data, serializer = serializer)
        return asymmetricCipher.doFinal(array)
    }

    override suspend fun <T> asymmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<T>,
        privateKey: PrivateKey
    ): T {
        asymmetricCipher.init(Cipher.DECRYPT_MODE, privateKey)
        val array = asymmetricCipher.doFinal(data)
        return jsonMapper.byteArrayToObject(data = array, deserializer = deserializer)
    }

    override suspend fun <T> symmetricDecrypt(data: ByteArray, deserializer: KSerializer<T>, key: Key): T {
        val array = symmetricDecrypt(data = data, key = key)
        return jsonMapper.byteArrayToObject(data = array, deserializer = deserializer)
    }

    override suspend fun symmetricDecrypt(data: ByteArray, key: Key): ByteArray {
        symmetricCipher.init(Cipher.DECRYPT_MODE, key)
        return symmetricCipher.doFinal(data)
    }

    override suspend fun <T> symmetricEncrypt(data: T, serializer: KSerializer<T>, key: Key): ByteArray {
        val array = jsonMapper.objectToByteArray(data = data, serializer = serializer)
        return symmetricEncrypt(data = array, key = key)
    }

    override suspend fun symmetricEncrypt(data: ByteArray, key: Key): ByteArray {
        symmetricCipher.init(Cipher.ENCRYPT_MODE, key)
        return symmetricCipher.doFinal(data)
    }
}