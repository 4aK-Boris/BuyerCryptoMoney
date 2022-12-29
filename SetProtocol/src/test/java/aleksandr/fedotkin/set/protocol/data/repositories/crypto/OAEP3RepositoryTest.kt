package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEP3Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP3Repository
import javax.crypto.SecretKey
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class OAEP3RepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<OAEP3Repository<TestModel, Test>> { parametersOf(testMapper) }

    private val keyRepository by inject<KeyRepository>()

    fun create(
        secretKey: SecretKey = keyRepository.generateSecretKey(),
        data: TestModel
    ): OAEP3Model<TestModel> {
        return OAEP3Model(secretKey = secretKey, data = data)
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val data = generateTestModel()
        val model = create(secretKey = secretKey, data = data)
        assertEquals(expected = model.secretKey, actual = secretKey)
        assertEquals(expected = model.data, actual = data)
    }

    @org.junit.Test
    fun testEncryptAndDecrypt() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val data = generateTestModel()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = create(secretKey = secretKey, data = data)
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
        assertEquals(expected = model.data, actual = data)
    }
}