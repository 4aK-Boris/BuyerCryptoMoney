package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP3Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEP3Repository<T: Model, R: DTO>: BaseSetRepository<OAEP3Model<T>, OAEP3<R>> {

    suspend fun create(secretKey: SecretKey, data: T): OAEP3Model<T>

    suspend fun encrypt(model: OAEP3Model<T>, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP3Model<T>
}
