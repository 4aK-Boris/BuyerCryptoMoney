package aleksandr.fedotkin.buyercryptomoney

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest: KoinComponent {
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
