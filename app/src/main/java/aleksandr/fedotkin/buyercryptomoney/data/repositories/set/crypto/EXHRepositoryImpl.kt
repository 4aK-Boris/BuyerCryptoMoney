package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CipherRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.EXHRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.OAEPRepository
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

class EXHRepositoryImpl(
    private val jsonMapper: JsonMapper,
    private val cipherRepository: CipherRepository,
    private val keyRepository: KeyRepository,
    private val messageDigestRepository: MessageDigestRepository,
    private val oaepRepository: OAEPRepository
) : EXHRepository {

    override suspend fun <T, R, S, K> encrypt(
        publicKey: PublicKey,
        data: T,
        secondaryData: S,
        map: (T) -> R,
        secondaryMap: (S) -> K,
        serializer: KSerializer<R>,
        secondarySerializer: KSerializer<K>
    ): CryptoDataModel {
        val dataArray = dataModelToByteArray(data = data, map = map, serializer = serializer)
        val secondaryDataArray = dataModelToByteArray(
            data = secondaryData,
            map = secondaryMap,
            serializer = secondarySerializer
        )
        val (cipherSharedData, secretKey) = encryptSharedData(
            data = dataArray,
            secondaryData = secondaryDataArray
        )
        val cipherOAEP = oaepRepository.createAndEncryptOAEPModel(
            secretKey = secretKey,
            hash = messageDigestRepository.messageDigest(data = dataArray),
            p = secondaryData,
            map = secondaryMap,
            serializer = secondarySerializer,
            publicKey = publicKey
        )
        return CryptoDataModel(cipherData = cipherSharedData, oaep = cipherOAEP)
    }

    override suspend fun <T, R, S, K> decrypt(
        privateKey: PrivateKey,
        cryptoDataModel: CryptoDataModel,
        map: (T) -> R,
        secondaryMap: (S) -> K,
        reverseSecondaryMap: (K) -> S,
        serializer: KSerializer<T>,
        secondarySerializer: KSerializer<S>
    ): Pair<R, K> {
        val oaepModel = oaepRepository.decryptOAEPModel(
            cipherOAEP = cryptoDataModel.oaep,
            privateKey = privateKey,
            serializer = secondarySerializer,
            map = secondaryMap
        )
        val secondaryData = dataModelToByteArray(
            data = oaepModel.p,
            map = reverseSecondaryMap,
            serializer = secondarySerializer
        )
        val clearSharedData = cipherRepository.symmetricDecrypt(
            data = cryptoDataModel.cipherData,
            key = oaepModel.secretKey
        )
        val dataModel = byteArrayToDataModel(
            byteArray = clearSharedData.take(clearSharedData.size - secondaryData.size)
                .toByteArray(), map = map, serializer = serializer
        )
        return dataModel to oaepModel.p
    }

    private suspend fun encryptSharedData(
        data: ByteArray,
        secondaryData: ByteArray
    ): Pair<ByteArray, SecretKey> {
        return generateSecretKey().run {
            cipherRepository.symmetricEncrypt(
                data = data + secondaryData,
                key = this
            ) to this
        }
    }

    private suspend fun generateSecretKey(): SecretKey {
        return keyRepository.generateSecretKey()
    }

    private fun <T, R> dataModelToByteArray(
        data: T,
        map: (T) -> R,
        serializer: KSerializer<R>
    ): ByteArray {
        return jsonMapper.objectToByteArray(data = map(data), serializer = serializer)
    }

    private fun <T, R> byteArrayToDataModel(
        byteArray: ByteArray,
        map: (T) -> R,
        serializer: KSerializer<T>
    ): R {
        return map(jsonMapper.byteArrayToObject(data = byteArray, deserializer = serializer))
    }
}
