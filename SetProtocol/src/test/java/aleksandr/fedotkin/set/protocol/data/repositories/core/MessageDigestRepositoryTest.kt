package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.MessageDigestRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.reflect.KClass
import kotlin.test.assertTrue

class MessageDigestRepositoryTest: BaseTestRepository() {

    override val clazz: KClass<*> = this::class

    override val repository by inject<MessageDigestRepository<TestModel, Test>> { parametersOf(testMapper) }

    @org.junit.Test
    fun testModel() = runBlocking {
        val model = generateTestModel()
        val messageDigest = repository.messageDigest(data = model)
        val verify = repository.verify(data = model, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testDTO() = runBlocking {
        val dto = generateTestDTO()
        val messageDigest = repository.messageDigest(data = dto)
        val verify = repository.verify(data = dto, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }

    @org.junit.Test
    fun testByteArray() = runBlocking {
        val array = generateByteArray(size = 1024)
        val messageDigest = repository.messageDigest(data = array)
        val verify = repository.verify(data = array, messageDigest = messageDigest)
        assertTrue(actual = verify)
    }
}