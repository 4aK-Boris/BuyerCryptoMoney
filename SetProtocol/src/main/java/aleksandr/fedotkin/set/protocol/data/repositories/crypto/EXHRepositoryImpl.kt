package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EXHMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EXHModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.MessageDigestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SymmetricCipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP4Repository
import java.security.PrivateKey
import java.security.PublicKey

class EXHRepositoryImpl<T : Model, S : Model, R : DTO, K : DTO>(
    override val mapper: EXHMapper,
    private val jsonMapper: JsonModelByteArrayMapper<T, R>,
    private val secondJsonMapper: JsonModelByteArrayMapper<S, K>,
    private val cipherRepository: SymmetricCipherRepository<T, R>,
    private val keyRepository: KeyRepository,
    private val messageDigestRepository: MessageDigestRepository<T, R>,
    private val oaepRepository: OAEP4Repository<S, K>
) : EXHRepository<T, S> {

    override suspend fun encrypt(publicKey: PublicKey, data: T, secondaryData: S): EXHModel {
        val dataByteArray = jsonMapper.map(value = data)
        val secondaryByteArray = secondJsonMapper.map(value = secondaryData)
        val secretKey = keyRepository.generateSecretKey()
        val cipherData = cipherRepository.encrypt(
            data = dataByteArray + secondaryByteArray,
            secretKey = secretKey
        )
        val oaepModel = oaepRepository.create(
            secretKey = secretKey,
            hash = messageDigestRepository.messageDigest(data = data),
            data = secondaryData
        )
        val cipherOAEP = oaepRepository.encrypt(model = oaepModel, publicKey = publicKey)
        return EXHModel(data = cipherData, secretKey = cipherOAEP)
    }

    override suspend fun decrypt(privateKey: PrivateKey, model: EXHModel): Pair<T, S> {
        val oaepModel = oaepRepository.decrypt(data = model.secretKey, privateKey = privateKey)
        val clearData = cipherRepository.decrypt(data = model.data, secretKey = oaepModel.secretKey)
        val size = clearData.size - secondJsonMapper.map(value = oaepModel.data).size
        val data = jsonMapper.reverseMap(value = clearData.copyOf(newSize = size))
        return data to oaepModel.data
    }
}