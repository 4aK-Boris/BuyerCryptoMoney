package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SignatureRepository
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Signature

class SignatureRepositoryImpl<T : Model, R : DTO>(
    private val signature: Signature,
    private val jsonByteArrayMapper: JsonByteArrayMapper<R>,
    private val jsonModelByteArrayMapper: JsonModelByteArrayMapper<T, R>,
    private val secureRandom: SecureRandom
) : SignatureRepository<T, R> {

    override suspend fun create(
        data: T,
        privateKey: PrivateKey
    ): ByteArray {
        return create(data = jsonModelByteArrayMapper.map(value = data), privateKey = privateKey)
    }

    override suspend fun create(
        data: R,
        privateKey: PrivateKey
    ): ByteArray {
        return create(
            data = jsonByteArrayMapper.map(value = data),
            privateKey = privateKey
        )
    }

    override suspend fun create(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray {
        return signature.run {
            initSign(privateKey, secureRandom)
            update(data)
            sign()
        }
    }

    override suspend fun verify(
        data: T,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return verify(
            data = jsonModelByteArrayMapper.map(value = data),
            publicKey = publicKey,
            signatureArray = signatureArray
        )
    }

    override suspend fun verify(
        data: R,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return verify(
            data = jsonByteArrayMapper.map(value = data,), publicKey = publicKey, signatureArray = signatureArray
        )
    }

    override suspend fun verify(
        data: ByteArray,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return signature.run {
            initVerify(publicKey)
            update(data)
            verify(signatureArray)
        }
    }
}