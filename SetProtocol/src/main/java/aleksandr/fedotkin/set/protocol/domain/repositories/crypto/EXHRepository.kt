package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EXHModel
import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.serialization.KSerializer

interface EXHRepository {

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> encrypt(
        publicKey: PublicKey,
        data: T,
        secondaryData: S,
        map: (T) -> R,
        secondaryMap: (S) -> K,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>
    ): EXHModel

    suspend fun <T : Model, S : Model, R : DTO, K : DTO> decrypt(
        privateKey: PrivateKey,
        model: EXHModel,
        map: (R) -> T,
        secondaryMap: (K) -> S,
        reverseSecondaryMap: (S) -> K,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>
    ): Pair<T, S>
}
