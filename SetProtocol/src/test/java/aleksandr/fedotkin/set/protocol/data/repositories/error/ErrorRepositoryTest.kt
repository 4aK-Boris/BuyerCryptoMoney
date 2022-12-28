package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageWrapperRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertTrue

class ErrorRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<ErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val messageWrapperRepositoryTest by inject<MessageWrapperRepositoryTest>()
    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testCreateAnSignature() = runBlocking {
        val testModel = generateTestModel()
        val messageWrapperModel =
            messageWrapperRepositoryTest.generateMessageWrapperModel(model = testModel)
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val errorCode = ErrorCode.UnknownXID
        val json = repository.createErrorMessage(
            errorCode = errorCode,
            publicKey = publicKey,
            privateKey = privateKey,
            messageWrapperModel = messageWrapperModel
        )
        val verify = repository.verifyError(json = json)
        assertTrue(actual = verify)
    }
}