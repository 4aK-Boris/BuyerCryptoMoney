package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEPMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEPModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEPRepository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

class OAEPRepositoryImpl(
    override val mapper: OAEPMapper,
    private val cipherRepository: AsymmetricCipherRepository<OAEPModel, OAEP>
): OAEPRepository {

    override suspend fun create(secretKey: SecretKey): OAEPModel {
        return OAEPModel(secretKey = secretKey)
    }

    override suspend fun encrypt(model: OAEPModel, publicKey: PublicKey): ByteArray {
        return cipherRepository.encrypt(data = model, publicKey = publicKey)
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEPModel {
        return cipherRepository.decryptModel(data = data, privateKey = privateKey)
    }
}