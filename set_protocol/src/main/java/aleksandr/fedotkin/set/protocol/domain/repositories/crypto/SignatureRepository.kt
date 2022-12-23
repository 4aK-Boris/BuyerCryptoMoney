package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.serialization.KSerializer

interface SignatureRepository {

    suspend fun <T> verifySignature(
        data: T,
        serializer: KSerializer<T>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun <T> createSignature(
        data: T,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): ByteArray
}