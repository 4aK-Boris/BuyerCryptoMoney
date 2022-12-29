package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.Enc
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncModel
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface EncRepository {

    val serializer: KSerializer<Enc>

    val convertToModel: (Enc) -> EncModel

    val convertToDTO: (EncModel) -> Enc

    suspend fun <T : Model, R : DTO> encrypt(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        certificate: X509Certificate,
        privateKey: PrivateKey
    ): EncModel

    suspend fun <T : Model, R : DTO> encrypt(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        privateKey: PrivateKey
    ): EncModel

    suspend fun <T : Model, R : DTO> decrypt(
        enc: Enc,
        map: (R) -> T,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        privateKey: PrivateKey
    ): T
}