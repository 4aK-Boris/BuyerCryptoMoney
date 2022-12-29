package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP3
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP3Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface OAEP3Repository {

    suspend fun <T: Model> createOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T
    ): OAEP3Model<T>

    suspend fun <T: Model, R: DTO> createAndEncryptOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray

    suspend fun <T: Model, R: DTO> encryptOAEPModel(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray

    suspend fun <T: Model, R: DTO> decryptOAEPModel(
        cipherOAEP: ByteArray,
        privateKey: PrivateKey,
        serializer: KSerializer<R>,
        map: (R) -> T
    ): OAEP3Model<T>

    fun <T: Model, R: DTO> convertToModel(
        oaep: OAEP3,
        map: (R) -> T,
        serializer: KSerializer<R>
    ): OAEP3Model<T>

    fun <T: Model, R: DTO> convertToDTO(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): OAEP3
}
