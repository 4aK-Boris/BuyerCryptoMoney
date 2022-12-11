package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.OAEP3
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.CryptoDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.OAEPMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.OAEP3Model
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CipherRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.EXHRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.MessageDigestRepository
import java.security.PublicKey
import kotlinx.serialization.KSerializer

class EXHRepositoryImpl(
    private val jsonMapper: JsonMapper,
    private val cipherRepository: CipherRepository,
    private val keyRepository: KeyRepository,
    private val messageDigestRepository: MessageDigestRepository,
    private val oaepMapper: OAEPMapper
): EXHRepository {

    override suspend fun <T, P, TD, PD> encrypt(
        r: PublicKey,
        t: T,
        p: P,
        mapT: (T) -> TD,
        mapP: (P) -> PD,
        tSerializer: KSerializer<TD>,
        pSerializer: KSerializer<PD>
    ): CryptoDataModel {
        val tArray = jsonMapper.objectToByteArray(data = mapT(t), serializer = tSerializer)
        val pArray = jsonMapper.objectToByteArray(data = mapP(p), serializer = pSerializer)
        val array = tArray + pArray
        val key = keyRepository.generateSecretKey()
        val cryptoArray = cipherRepository.symmetricEncrypt(data = array, key = key)
        val hash = messageDigestRepository.messageDigest(data = mapT(t), serializer = tSerializer)
        val oaepModel = OAEP3Model(key = key, hash = hash, p = p)
        val oaep = oaepMapper.map(model = oaepModel, map = mapP, serializer = pSerializer)
        val oaepCrypto = cipherRepository.asymmetricEncrypt(data = oaep, serializer = OAEP3.serializer(), publicKey = r)
        return CryptoDataModel(cipherData = cryptoArray, oaep = oaepCrypto)
    }
}