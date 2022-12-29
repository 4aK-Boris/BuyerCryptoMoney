package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SymmetricCipherRepository
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.SecretKey

class SymmetricCipherRepositoryImpl<T : Model, R : DTO>(
    private val cipher: Cipher,
    private val jsonByteArrayMapper: JsonByteArrayMapper<R>,
    private val jsonModelByteArrayMapper: JsonModelByteArrayMapper<T, R>,
) : SymmetricCipherRepository<T, R> {

    override suspend fun encrypt(data: T, secretKey: SecretKey): ByteArray {
        return encrypt(data = jsonModelByteArrayMapper.map(value = data), secretKey = secretKey)
    }

    override suspend fun encrypt(data: R, secretKey: SecretKey): ByteArray {
        return encrypt(data = jsonByteArrayMapper.map(value = data), secretKey = secretKey)
    }

    override suspend fun encrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
        return cipher.run {
            init(ENCRYPT_MODE, secretKey)
            doFinal(data)
        }
    }

    override suspend fun decryptModel(data: ByteArray, secretKey: SecretKey): T {
        return jsonModelByteArrayMapper.reverseMap(
            value = decrypt(
                data = data,
                secretKey = secretKey
            )
        )
    }

    override suspend fun decryptDTO(data: ByteArray, secretKey: SecretKey): R {
        return jsonByteArrayMapper.reverseMap(value = decrypt(data = data, secretKey = secretKey))
    }

    override suspend fun decrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
        return cipher.run {
            init(DECRYPT_MODE, secretKey)
            doFinal(data)
        }
    }
}