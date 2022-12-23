package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP3
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP3Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface OAEPRepository {

    suspend fun <T> createOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T
    ): OAEP3Model<T>

    suspend fun <T, R> createAndEncryptOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray

    suspend fun <T, R> encryptOAEPModel(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray

    suspend fun <T, R> decryptOAEPModel(
        cipherOAEP: ByteArray,
        privateKey: PrivateKey,
        serializer: KSerializer<R>,
        map: (R) -> T
    ): OAEP3Model<T>

    fun <T, R> convertToModel(
        oaep: OAEP3,
        map: (T) -> R,
        serializer: KSerializer<T>
    ): OAEP3Model<R>

    fun <T, R> convertToDTO(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): OAEP3
}
