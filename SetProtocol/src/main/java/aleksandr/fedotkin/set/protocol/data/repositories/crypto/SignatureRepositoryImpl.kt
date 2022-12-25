package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Signature
import kotlinx.serialization.KSerializer

class SignatureRepositoryImpl(
    private val signature: Signature,
    private val jsonMapper: JsonMapper,
    private val secureRandom: SecureRandom
) : SignatureRepository {

    override suspend fun <T : Model, R : DTO> verifySignature(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return verifySignature(
            data = map(model),
            serializer = serializer,
            publicKey = publicKey,
            signatureArray = signatureArray
        )
    }

    override suspend fun <T : DTO> verifySignature(
        data: T,
        serializer: KSerializer<T>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return verifySignature(
            data = jsonMapper.objectToByteArray(
                data = data,
                serializer = serializer
            ), publicKey = publicKey, signatureArray = signatureArray
        )
    }

    override suspend fun verifySignature(
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

    override suspend fun <T : Model, R : DTO> createSignature(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ByteArray {
        return createSignature(data = map(model), serializer = serializer, privateKey = privateKey)
    }

    override suspend fun <T : DTO> createSignature(
        data: T,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): ByteArray {
        return createSignature(
            data = jsonMapper.objectToByteArray(data = data, serializer = serializer),
            privateKey = privateKey
        )
    }

    override suspend fun createSignature(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray {
        return signature.run {
            initSign(privateKey, secureRandom)
            update(data)
            sign()
        }
    }
}