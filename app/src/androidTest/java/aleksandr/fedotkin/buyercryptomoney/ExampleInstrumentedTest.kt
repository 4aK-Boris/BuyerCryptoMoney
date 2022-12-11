package aleksandr.fedotkin.buyercryptomoney

import aleksandr.fedotkin.buyercryptomoney.data.network.plugins.test.Box
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.koin.core.Koin

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("aleksandr.fedotkin.buyercryptomoney", appContext.packageName)
    }

    @Test
    fun test() {
//        val k = createMessageWrapperModel()
//        val t = messageWrapperMapper.map(messageWrapperModel = k, map = cardCInitReqMapper::map)

    }
}