package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.serialization.KSerializer

interface EXHRepository {

    suspend fun <T, R, S, K> encrypt(
        publicKey: PublicKey,
        data: T,
        secondaryData: S,
        map: (T) -> R,
        secondaryMap: (S) -> K,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>
    ): CryptoDataModel

    suspend fun <T, R, S, K> decrypt(
        privateKey: PrivateKey,
        cryptoDataModel: CryptoDataModel,
        map: (T) -> R,
        secondaryMap: (S) -> K,
        reverseSecondaryMap: (K) -> S,
        serializer: KSerializer<T>,
        secondarySerializer: KSerializer<S>
    ): Pair<R, K>
}
