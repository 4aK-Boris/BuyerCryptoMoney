package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.EXH
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EXHModel
import java.security.PrivateKey
import java.security.PublicKey

interface EXHRepository<T : Model, S : Model>: BaseSetRepository<EXHModel, EXH> {

    suspend fun encrypt(publicKey: PublicKey, data: T, secondaryData: S): EXHModel

    suspend fun decrypt(privateKey: PrivateKey, model: EXHModel, ): Pair<T, S>
}
