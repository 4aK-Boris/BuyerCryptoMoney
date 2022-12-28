package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.crypto.OAEP3
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.OAEPMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.crypto.OAEP3Model
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP3Repository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

open class OAEP3RepositoryImpl(
    private val oaepMapper: OAEPMapper,
    private val cipherRepository: CipherRepository
) : OAEP3Repository {

    override suspend fun <T: Model> createOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T
    ): OAEP3Model<T> {
        return OAEP3Model(
            secretKey = secretKey,
            hash = hash,
            p = p
        )
    }

    override suspend fun <T: Model, R: DTO> createAndEncryptOAEPModel(
        secretKey: SecretKey,
        hash: ByteArray,
        p: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray {
        return encryptOAEPModel(
            oaepModel = createOAEPModel(secretKey = secretKey, hash = hash, p = p),
            map = map,
            serializer = serializer,
            publicKey = publicKey
        )
    }

    override suspend fun <T: Model, R: DTO> encryptOAEPModel(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray {
        return cipherRepository.asymmetricEncrypt(
            data = convertToDTO(
                oaepModel = oaepModel,
                map = map,
                serializer = serializer
            ), publicKey = publicKey, serializer = OAEP3.serializer()
        )
    }

    override suspend fun <T: Model, R: DTO> decryptOAEPModel(
        cipherOAEP: ByteArray,
        privateKey: PrivateKey,
        serializer: KSerializer<R>,
        map: (R) -> T
    ): OAEP3Model<T> {
        return convertToModel(
            oaep = cipherRepository.asymmetricDecrypt(
                data = cipherOAEP,
                privateKey = privateKey,
                deserializer = OAEP3.serializer()
            ), map = map, serializer = serializer
        )
    }

    override fun <T: Model, R: DTO> convertToModel(
        oaep: OAEP3,
        map: (R) -> T,
        serializer: KSerializer<R>
    ): OAEP3Model<T> {
        return oaepMapper.map(dto = oaep, map = map, serializer = serializer)
    }

    override fun <T: Model, R: DTO> convertToDTO(
        oaepModel: OAEP3Model<T>,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): OAEP3 {
        return oaepMapper.map(model = oaepModel, map = map, serializer = serializer)
    }
}