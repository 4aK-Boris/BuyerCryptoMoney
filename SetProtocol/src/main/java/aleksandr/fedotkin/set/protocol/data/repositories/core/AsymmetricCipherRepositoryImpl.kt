package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.AsymmetricCipherRepository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

class AsymmetricCipherRepositoryImpl<T : Model, R : DTO>(
    private val cipher: Cipher,
    private val jsonByteArrayMapper: JsonByteArrayMapper<R>,
    private val jsonModelByteArrayMapper: JsonModelByteArrayMapper<T, R>,
) : AsymmetricCipherRepository<T, R> {

    override suspend fun encrypt(data: T, publicKey: PublicKey): ByteArray {
        return encrypt(data = jsonModelByteArrayMapper.map(value = data), publicKey = publicKey)
    }

    override suspend fun encrypt(data: R, publicKey: PublicKey): ByteArray {
        return encrypt(data = jsonByteArrayMapper.map(value = data), publicKey = publicKey)
    }

    override suspend fun encrypt(data: ByteArray, publicKey: PublicKey): ByteArray {
        return cipher.run {
            init(Cipher.ENCRYPT_MODE, publicKey)
            doFinal(data)
        }
    }

    override suspend fun decryptModel(data: ByteArray, privateKey: PrivateKey): T {
        return jsonModelByteArrayMapper.reverseMap(
            value = decrypt( data = data,privateKey = privateKey )
        )
    }

    override suspend fun decryptDTO(data: ByteArray, privateKey: PrivateKey): R {
        return jsonByteArrayMapper.reverseMap(value = decrypt(data = data, privateKey = privateKey))
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): ByteArray {
        return cipher.run {
            init(Cipher.DECRYPT_MODE, privateKey)
            doFinal(data)
        }
    }
}