package aleksandr.fedotkin.buyercryptomoney

import aleksandr.fedotkin.buyercryptomoney.core.di.appModule
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest: KoinTest {

    private val networkApi by inject<SetNetworkAPI>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(appModule)
    }

    @Test
    fun test() {
        println(networkApi)
    }
}