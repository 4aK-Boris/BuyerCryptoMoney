package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP2
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP2Model
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface OAEP2Repository {

    fun <T: Model, R: DTO> convertToModel(
        oaep2: OAEP2,
        map: (R) -> T,
        serializer: KSerializer<R>
    ): OAEP2Model<T>

    fun <T: Model, R: DTO> convertToDTO(
        oaep2Model: OAEP2Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): OAEP2

    suspend fun <T : Model, R : DTO> createAndEncrypt(
        secretKey: SecretKey,
        data: T,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): ByteArray

    suspend fun <T : Model, R : DTO> encrypt(
        model: OAEP2Model<T>,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>): ByteArray

    suspend fun <T : Model, R : DTO> encrypt(
        model: OAEP2Model<T>,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): ByteArray

    suspend fun <T : Model, R : DTO> decrypt(
        data: ByteArray,
        privateKey: PrivateKey,
        map: (R) -> T,
        serializer: KSerializer<R>
    ): OAEP2Model<T>
}