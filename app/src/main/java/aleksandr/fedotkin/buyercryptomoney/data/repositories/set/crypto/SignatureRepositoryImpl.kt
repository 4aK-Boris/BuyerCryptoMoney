package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.SignatureRepository
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import java.security.Signature
import kotlinx.serialization.KSerializer

class SignatureRepositoryImpl(
    private val signature: Signature,
    private val jsonMapper: JsonMapper,
    private val secureRandom: SecureRandom
): SignatureRepository {

    override suspend fun <T> verifySignature(
        data: T,
        serializer: KSerializer<T>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean {
        return signature.run {
           initVerify(publicKey)
           update(jsonMapper.objectToByteArray(data = data, serializer = serializer))
           verify(signatureArray)
        }
    }

    override suspend fun <T> createSignature(
        data: T,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): ByteArray {
        val array = jsonMapper.objectToByteArray(data = data, serializer = serializer)
        return signature.run {
            initSign(privateKey, secureRandom)
            update(array)
            sign()
        }
    }
}