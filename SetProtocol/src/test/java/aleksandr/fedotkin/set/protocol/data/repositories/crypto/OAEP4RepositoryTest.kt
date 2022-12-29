package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP4Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP4Repository
import javax.crypto.SecretKey
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class OAEP4RepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<OAEP4Repository<TestModel, Test>> { parametersOf(testMapper) }

    private val keyRepository by inject<KeyRepository>()

    fun create(
        secretKey: SecretKey = keyRepository.generateSecretKey(),
        hash: ByteArray = generateByteArray(size = 32),
        data: TestModel
    ): OAEP4Model<TestModel> {
        return OAEP4Model(secretKey = secretKey, data = data, hash = hash)
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val hash = generateByteArray(size = 32)
        val data = generateTestModel()
        val model = create(secretKey = secretKey, data = data, hash = hash)
        assertEquals(expected = model.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
        assertEquals(expected = model.data, actual = data)
    }

    @org.junit.Test
    fun testEncryptAndDecrypt() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val hash = generateByteArray(size = 32)
        val data = generateTestModel()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = create(secretKey = secretKey, data = data, hash = hash)
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
        assertContentEquals(expected = model.hash, actual = hash)
        assertEquals(expected = model.data, actual = data)
    }
}