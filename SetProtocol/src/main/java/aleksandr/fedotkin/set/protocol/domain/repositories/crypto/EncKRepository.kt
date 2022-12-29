package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncK
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncKModel
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface EncKRepository {

    val serializer: KSerializer<EncK>

    val convertToModel: (EncK) -> EncKModel

    val convertToDTO: (EncKModel) -> EncK

    suspend fun <T : Model, R : DTO> encrypt(
        data: T,
        secretKey: SecretKey,
        privateKey: PrivateKey,
        serializer: KSerializer<R>,
        map: (T) -> R
    ): EncKModel

    suspend fun <T : Model, R : DTO> decrypt(
        model: EncKModel,
        secretKey: SecretKey,
        certificate: X509Certificate,
        serializer: KSerializer<R>,
        map: (R) -> T
    ): T

    suspend fun <T : Model, R : DTO> decrypt(
        model: EncKModel,
        secretKey: SecretKey,
        publicKey: PublicKey,
        serializer: KSerializer<R>,
        map: (R) -> T
    ): T
}