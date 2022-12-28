package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SignatureRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.reflect.KClass
import kotlin.test.assertTrue

class SignatureRepositoryTest : BaseTestRepository() {

    override val clazz: KClass<*> = this::class

    override val repository by inject<SignatureRepository<TestModel, Test>> {
        parametersOf(testMapper)
    }

    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testModel() = runBlocking {
        val model = generateTestModel()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val signature = repository.create(data = model, privateKey = privateKey)
        val verify =
            repository.verify(data = model, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val dto = generateTestDTO()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val signature = repository.create(data = dto, privateKey = privateKey)
        val verify =
            repository.verify(data = dto, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val array = generateByteArray(size = 1024)
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val signature = repository.create(data = array, privateKey = privateKey)
        val verify =
            repository.verify(data = array, publicKey = publicKey, signatureArray = signature)
        assertTrue(actual = verify)
    }
}