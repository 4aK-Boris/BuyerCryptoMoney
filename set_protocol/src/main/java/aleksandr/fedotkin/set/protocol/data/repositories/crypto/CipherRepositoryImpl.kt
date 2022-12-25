package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CipherRepository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

class CipherRepositoryImpl(
    private val asymmetricCipher: Cipher,
    private val symmetricCipher: Cipher,
    private val jsonMapper: JsonMapper
) : CipherRepository {

    override suspend fun <T: Model, R: DTO> asymmetricEncrypt(
        model: T,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        map: (T) -> R
    ): ByteArray {
        return asymmetricEncrypt(data = map(model), serializer = serializer, publicKey = publicKey)
    }

    override suspend fun <T: Model, R: DTO> asymmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<R>,
        privateKey: PrivateKey,
        map: (R) -> T
    ): T {
        return map(asymmetricDecrypt(data = data, deserializer = deserializer, privateKey = privateKey))
    }

    override suspend fun <R: DTO> asymmetricEncrypt(
        data: R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray {
        return asymmetricEncrypt(
            data = jsonMapper.objectToByteArray(
                data = data,
                serializer = serializer
            ), publicKey = publicKey
        )
    }

    override suspend fun <R: DTO> asymmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<R>,
        privateKey: PrivateKey
    ): R {
        return jsonMapper.byteArrayToObject(
            data = asymmetricDecrypt(
                data = data,
                privateKey = privateKey
            ), deserializer = deserializer
        )
    }

    override suspend fun asymmetricEncrypt(
        data: ByteArray,
        publicKey: PublicKey
    ): ByteArray {
        return asymmetricCipher.run {
            init(ENCRYPT_MODE, publicKey)
            asymmetricCipher.doFinal(data)
        }
    }

    override suspend fun asymmetricDecrypt(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray {
        return asymmetricCipher.run {
            init(DECRYPT_MODE, privateKey)
            asymmetricCipher.doFinal(data)
        }
    }

    override suspend fun <T : Model, R : DTO> symmetricEncrypt(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        secretKey: SecretKey
    ): ByteArray {
        return symmetricEncrypt(data = map(model), serializer = serializer, secretKey = secretKey)
    }

    override suspend fun <T : Model, R : DTO> symmetricDecrypt(
        data: ByteArray,
        map: (R) -> T,
        deserializer: KSerializer<R>,
        secretKey: SecretKey
    ): T {
        return map(
            symmetricDecrypt(
                data = data, deserializer = deserializer, secretKey = secretKey
            )
        )
    }

    override suspend fun <T : DTO> symmetricEncrypt(
        data: T,
        serializer: KSerializer<T>,
        secretKey: SecretKey
    ): ByteArray {
        return symmetricEncrypt(
            data = jsonMapper.objectToByteArray(
                data = data, serializer = serializer
            ), secretKey = secretKey
        )
    }

    override suspend fun <T : DTO> symmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<T>,
        secretKey: SecretKey
    ): T {
        return jsonMapper.byteArrayToObject(
            data = symmetricDecrypt(data = data, secretKey = secretKey), deserializer = deserializer
        )
    }

    override suspend fun symmetricEncrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
        return symmetricCipher.run {
            init(ENCRYPT_MODE, secretKey)
            doFinal(data)
        }
    }

    override suspend fun symmetricDecrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
        return symmetricCipher.run {
            init(DECRYPT_MODE, secretKey)
            doFinal(data)
        }
    }
}
