package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncX
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncXModel
import java.security.PrivateKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface EncXRepository {

    val serializer: KSerializer<EncX>

    val convertToModel: (EncX) -> EncXModel

    val convertToDTO: (EncXModel) -> EncX

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> encrypt(
        data: T,
        secondaryData: S,
        privateKey: PrivateKey,
        certificate: X509Certificate,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>,
        map: (T) -> R,
        secondaryMap: (S) -> K
    ): EncXModel

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> decrypt(
        encXModel: EncXModel,
        privateKey: PrivateKey,
        certificate: X509Certificate,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>,
        map: (R) -> T,
        secondaryMap: (K) -> S
    ): Pair<T, S>
}