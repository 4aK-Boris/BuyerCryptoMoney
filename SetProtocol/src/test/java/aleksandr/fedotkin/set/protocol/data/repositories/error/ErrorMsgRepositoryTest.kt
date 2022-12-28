package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.general.MessageHeaderRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorMsgRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals

class ErrorMsgRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<ErrorMsgRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val messageHeaderRepositoryTest by inject<MessageHeaderRepositoryTest>()

    @org.junit.Test
    fun testCreate() = runBlocking {
        val testModel = generateTestModel()
        val messageHeader = messageHeaderRepositoryTest.createMessageHeader()
        val model = repository.create(message = testModel, messageHeader = messageHeader)
        assertEquals(expected = model.badWrapper, actual = testModel)
        assertEquals(expected = model.messageHeader, actual = messageHeader)
    }
}