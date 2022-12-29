package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEPModel
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

interface OAEPRepository: BaseSetRepository<OAEPModel, OAEP> {

    suspend fun create(secretKey: SecretKey): OAEPModel

    suspend fun encrypt(model: OAEPModel, publicKey: PublicKey): ByteArray

    suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEPModel
}