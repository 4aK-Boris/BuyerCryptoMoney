package aleksandr.fedotkin.buyercryptomoney

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.core.di.appModule
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageWrapperMapper
import aleksandr.fedotkin.buyercryptomoney.domain.models.set.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.set.general.MessageIDModel
import aleksandr.fedotkin.buyercryptomoney.domain.models.set.general.MessageWrapperModel
import java.math.BigInteger
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.get
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: KoinTest {

    private val messageWrapperMapper by lazy { get<MessageWrapperMapper>() }
    private val cardCInitReqMapper by lazy { get<CardCInitReqMapper>() }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
//        val k = createMessageWrapperModel()
//        val t = messageWrapperMapper.map(messageWrapperModel = k, map = cardCInitReqMapper::map)
//        val k = Box(5)
//        val json = Json.encodeToString(value = k)
//        println(json)
    }

    private fun createCardCInitReq(): CardCInitReqModel {
        return CardCInitReqModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE = generateNewNumber(),
            brandID = generateNewNumber(),
            thumbs = emptyList()
        )
    }

    private fun createMessageWrapperModel(): MessageWrapperModel<CardCInitReqModel> {
        return createCardCInitReq().run {
            MessageWrapperModel(
                mWExtension = null,
                messageModel = this,
                messageHeaderModel = MessageHeaderModel(
                    rrpId = this.rrpID,
                    messageIDModel = MessageIDModel(
                        lIdC = generateNewNumber(),
                        lIdM = generateNewNumber(),
                        xId = generateNewNumber()
                    )
                )
            )
        }
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(Random.nextBytes(NUMBER_LENGTH))
    }
}