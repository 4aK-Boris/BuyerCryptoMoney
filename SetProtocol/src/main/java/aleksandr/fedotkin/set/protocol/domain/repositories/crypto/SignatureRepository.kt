package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.serialization.KSerializer

interface SignatureRepository {

    suspend fun <T : Model, R: DTO> verifySignature(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun <T: DTO> verifySignature(
        data: T,
        serializer: KSerializer<T>,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun verifySignature(
        data: ByteArray,
        publicKey: PublicKey,
        signatureArray: ByteArray
    ): Boolean

    suspend fun <T : Model, R: DTO> createSignature(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun <T: DTO> createSignature(
        data: T,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun createSignature(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray
}