package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.AsymmetricCipherRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.reflect.KClass
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class AsymmetricCipherRepositoryTest: BaseTestRepository() {

    override val clazz: KClass<*> = this::class

    override val repository by inject<AsymmetricCipherRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testModel() = runBlocking {
        val model = generateTestModel()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val cipherData = repository.encrypt(data = model, publicKey = publicKey)
        val clearModel = repository.decryptModel(data = cipherData, privateKey = privateKey)
        assertEquals(expected = clearModel, actual = model)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val dto = generateTestDTO()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val cipherData = repository.encrypt(data = dto, publicKey = publicKey)
        val clearDTO = repository.decryptDTO(data = cipherData, privateKey = privateKey)
        assertEquals(expected = clearDTO, actual = dto)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val array = generateByteArray(size = 128)
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val cipherData = repository.encrypt(data = array, publicKey = publicKey)
        val clearArray = repository.decrypt(data = cipherData, privateKey = privateKey)
        assertContentEquals(expected = clearArray, actual = array)
    }
}