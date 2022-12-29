package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.UnsignedErrorRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class UnsignedErrorRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<UnsignedErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()
    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testCreate() = runBlocking {
        val testModel = generateTestModel()
        val messageHeader = messageHeaderRepositoryTest.createMessageHeader()
        val (publicKey, _) = keyRepository.generatePairKey()
        val errorCode = ErrorCode.UnknownXID
        val model = repository.create(message = testModel, messageHeader = messageHeader, publicKey = publicKey, errorCode = errorCode)
        assertEquals(expected = model.errorTBS.errorCode, actual = errorCode)
        assertContentEquals(expected = model.errorTBS.errorThumb, actual = publicKey.encoded)
        assertEquals(expected = model.errorTBS.errorMsg.messageHeader, actual = messageHeader)
        assertEquals(expected = model.errorTBS.errorMsg.badWrapper, actual = testModel)
    }
}