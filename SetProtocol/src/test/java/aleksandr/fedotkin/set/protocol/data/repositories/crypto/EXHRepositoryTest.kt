package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class EXHRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<EXHRepository<TestModel, TestModel>> { parametersOf(testMapper, testMapper) }

    private val keyRepository by inject<KeyRepository>()

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val testModel = generateTestModel()
        val secondaryTestModel = generateTestModel()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = repository.encrypt(
            publicKey = publicKey,
            data = testModel,
            secondaryData = secondaryTestModel
        )
        val (decryptTestModel, decryptSecondaryTestModel) = repository.decrypt(
            privateKey = privateKey,
            model = model
        )
        assertEquals(expected = decryptTestModel, actual = testModel)
        assertEquals(expected = decryptSecondaryTestModel, actual = secondaryTestModel)
    }
}