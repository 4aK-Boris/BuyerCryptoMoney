package aleksandr.fedotkin.set.protocol.data.di

import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.check.checkModules

class DiTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(setModule)
    }

    @Test
    fun testDI() {
        koinApplication {
            modules(setModule)
            checkModules()
        }
    }

    @Test
    fun testTestDI() {
        koinApplication {
            modules(testModule, setModule)
            checkModules()
        }
    }
}