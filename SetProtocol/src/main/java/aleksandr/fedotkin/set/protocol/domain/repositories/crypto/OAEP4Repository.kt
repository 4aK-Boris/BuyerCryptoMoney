package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP4
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP4Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEP4Repository<T: Model, R: DTO>: BaseSetRepository<OAEP4Model<T>, OAEP4<R>> {

    suspend fun create(secretKey: SecretKey, hash: ByteArray, data: T): OAEP4Model<T>

    suspend fun encrypt(model: OAEP4Model<T>, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP4Model<T>
}