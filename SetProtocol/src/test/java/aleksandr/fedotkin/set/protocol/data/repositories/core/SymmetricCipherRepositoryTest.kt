package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SymmetricCipherRepository
import javax.crypto.SecretKey
import kotlinx.coroutines.runBlocking
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.reflect.KClass
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class SymmetricCipherRepositoryTest: BaseTestRepository() {

    override val clazz: KClass<*> = this::class

    override val repository by inject<SymmetricCipherRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testModel() = runBlocking {
        val model = generateTestModel()
        val secretKey = generateSecretKey()
        val cipherData = repository.encrypt(data = model, secretKey = secretKey)
        val clearModel = repository.decryptModel(data = cipherData, secretKey = secretKey)
        assertEquals(expected = clearModel, actual = model)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val dto = generateTestDTO()
        val secretKey = generateSecretKey()
        val cipherData = repository.encrypt(data = dto, secretKey = secretKey)
        val clearDTO = repository.decryptDTO(data = cipherData, secretKey = secretKey)
        assertEquals(expected = clearDTO, actual = dto)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val array = generateByteArray(size = 1024)
        val secretKey = generateSecretKey()
        val cipherData = repository.encrypt(data = array, secretKey = secretKey)
        val clearArray = repository.decrypt(data = cipherData, secretKey = secretKey)
        assertContentEquals(expected = clearArray, actual = array)
    }

    private fun generateSecretKey(): SecretKey {
        return keyRepository.generateSecretKey()
    }
}