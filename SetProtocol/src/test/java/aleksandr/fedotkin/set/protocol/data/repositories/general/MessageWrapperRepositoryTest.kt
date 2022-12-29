package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.core.repository.EasyModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MessageWrapperRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<MessageWrapperRepository<TestModel, aleksandr.fedotkin.set.protocol.core.Test>> {
        parametersOf(
            testMapper
        )
    }

    suspend fun generateMessageWrapperModel(
        rrpid: BigInteger = generateNewNumber(),
        model: TestModel
    ): MessageWrapperModel<TestModel> {
        return repository.create(rrpid = rrpid, message = model)
    }

    @Test
    fun testCreate() = runBlocking {
        val rrpid = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        assertTrue(actual = model.mWExtension == null)
        assertEquals(expected = model.messageModel, actual = testModule)
        assertEquals(expected = model.messageHeaderModel.rrpId, actual = rrpid)
    }

    @Test
    fun testJsonConverter() = runBlocking {
        val rrpid = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        val json = repository.messageWrapperModelToJson(messageWrapperModel = model)
        val decodeModel = repository.jsonToMessageWrapperModel(json = json)
        assertEquals(expected = decodeModel, actual = model)
    }

    @Test
    fun testChangeRRPID() = runBlocking {
        val rrpid = generateNewNumber()
        val newRRPID = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        val newModel = repository.changeRRPID(rrpid = newRRPID, messageWrapperModel = model)
        assertEquals(expected = newModel.messageHeaderModel.rrpId, actual = newRRPID)
    }

    @Test
    fun testChangeMessageOnOther() = runBlocking {
        val rrpid = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        val newModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model
        )
        assertEquals(expected = newModel.messageModel, actual = EasyModel())
    }

    @Test
    fun testChangeMessageOnOtherAndRRPID() = runBlocking {
        val rrpid = generateNewNumber()
        val newRRPID = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        val newModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model,
            rrpid = newRRPID
        )
        assertEquals(expected = newModel.messageModel, actual = EasyModel())
        assertEquals(expected = newModel.messageHeaderModel.rrpId, actual = newRRPID)
    }

    @Test
    fun testChangeMessage() = runBlocking {
        val rrpid = generateNewNumber()
        val testModule = generateTestModel()
        val easyModel = EasyModel()
        val model = repository.create(rrpid = rrpid, message = testModule)
        val secondModel = repository.changeMessageModelOnOther(
            messageModel = easyModel,
            messageWrapperModel = model
        )
        assertEquals(expected = secondModel.messageModel, actual = easyModel)
        val thirdModel = repository.changeMessageModel(
            messageModel = testModule,
            messageWrapperModel = secondModel
        )
        assertEquals(expected = thirdModel.messageModel, actual = testModule)
    }

    @Test
    fun testChangeMessageAndRRPID() = runBlocking {
        val rrpid = generateNewNumber()
        val newRRPID = generateNewNumber()
        val testModule = generateTestModel()
        val model = repository.create(rrpid = newRRPID, message = testModule)
        val secondModel = repository.changeMessageModelOnOther(
            messageModel = EasyModel(),
            messageWrapperModel = model,
            rrpid = newRRPID
        )
        val thirdModel = repository.changeMessageModel(
            messageModel = testModule,
            messageWrapperModel = secondModel,
            rrpid = rrpid
        )
        assertEquals(expected = thirdModel.messageModel, actual = testModule)
        assertEquals(expected = thirdModel.messageHeaderModel.rrpId, actual = rrpid)
    }
}