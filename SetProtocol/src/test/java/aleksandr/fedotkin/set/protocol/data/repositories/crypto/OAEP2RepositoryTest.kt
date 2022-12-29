package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP2Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP2Repository
import javax.crypto.SecretKey
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class OAEP2RepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<OAEP2Repository>()

    private val keyRepository by inject<KeyRepository>()

    fun create(
        secretKey: SecretKey = keyRepository.generateSecretKey(),
        hash: ByteArray = generateByteArray(size = 32)
    ): OAEP2Model {
        return OAEP2Model(secretKey = secretKey, hash = hash)
    }

    @Test
    fun testCreate() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val hash = generateByteArray(size = 32)
        val model = create(secretKey = secretKey, hash = hash)
        assertEquals(expected = model.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
    }

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val hash = generateByteArray(size = 32)
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = create(secretKey = secretKey, hash = hash)
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
    }
}