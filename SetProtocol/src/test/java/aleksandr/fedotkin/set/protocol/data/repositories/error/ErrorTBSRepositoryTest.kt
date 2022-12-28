package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorTBSRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ErrorTBSRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<ErrorTBSRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()
    private val keyRepository by inject<KeyRepository>()

    suspend fun create(): ErrorTBSModel<TestModel> {
        val testModel = generateTestModel()
        val messageHeader = messageHeaderRepositoryTest.createMessageHeader()
        val (publicKey, _) = keyRepository.generatePairKey()
        val errorCode = ErrorCode.UnknownXID
        return repository.create(
            message = testModel,
            messageHeader = messageHeader,
            publicKey = publicKey,
            errorCode = errorCode
        )
    }

    @org.junit.Test
    fun testCreate() = runBlocking {
        val testModel = generateTestModel()
        val messageHeader = messageHeaderRepositoryTest.createMessageHeader()
        val (publicKey, _) = keyRepository.generatePairKey()
        val errorCode = ErrorCode.UnknownXID
        val model = repository.create(
            message = testModel,
            messageHeader = messageHeader,
            publicKey = publicKey,
            errorCode = errorCode
        )
        assertEquals(expected = model.errorCode, actual = errorCode)
        assertContentEquals(expected = model.errorThumb, actual = publicKey.encoded)
        assertEquals(expected = model.errorMsg.messageHeader, actual = messageHeader)
        assertEquals(expected = model.errorMsg.badWrapper, actual = testModel)
    }

    @org.junit.Test
    fun testDecodePublicKey() = runBlocking {
        val (publicKey, _) = keyRepository.generatePairKey()
        val model = repository.create(
            message = generateTestModel(),
            messageHeader = messageHeaderRepositoryTest.createMessageHeader(),
            publicKey = publicKey,
            errorCode = ErrorCode.UnknownXID
        )
        val decodePublicKey = repository.decodePublicKey(errorTBSModel = model)
        assertEquals(expected = decodePublicKey, actual = publicKey)
    }
}