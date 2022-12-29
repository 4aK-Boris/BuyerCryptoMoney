package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP2
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP2Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEP2Repository: BaseSetRepository<OAEP2Model, OAEP2> {

    suspend fun create(secretKey: SecretKey, hash: ByteArray): OAEP2Model

    suspend fun encrypt(model: OAEP2Model, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP2Model
}