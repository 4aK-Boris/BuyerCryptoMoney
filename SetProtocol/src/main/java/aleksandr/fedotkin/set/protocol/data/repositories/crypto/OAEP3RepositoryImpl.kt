package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.dto.crypto.oaep.OAEP3
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP3Mapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP3Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP3Repository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

class OAEP3RepositoryImpl<T: Model, R: DTO>(
    override val mapper: OAEP3Mapper<T, R>,
    private val cipherRepository: AsymmetricCipherRepository<OAEP3Model<T>, OAEP3<R>>
): OAEP3Repository<T, R> {

    override suspend fun create(secretKey: SecretKey, data: T): OAEP3Model<T> {
        return OAEP3Model(secretKey = secretKey, data = data)
    }

    override suspend fun encrypt(model: OAEP3Model<T>, publicKey: PublicKey): ByteArray {
        return cipherRepository.encrypt(data = model, publicKey = publicKey)
    }

    override suspend fun decrypt(data: ByteArray, privateKey: PrivateKey): OAEP3Model<T> {
        return cipherRepository.decryptModel(data = data, privateKey = privateKey)
    }
}
