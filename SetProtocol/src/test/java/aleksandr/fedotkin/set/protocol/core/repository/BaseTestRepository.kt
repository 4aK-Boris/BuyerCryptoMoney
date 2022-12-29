package aleksandr.fedotkin.set.protocol.core.repository

import aleksandr.fedotkin.set.protocol.core.NUMBER_LENGTH
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.di.setModule
import aleksandr.fedotkin.set.protocol.core.di.testModule
import java.math.BigInteger
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.hasAnnotation
import kotlin.test.assertEquals

abstract class BaseTestRepository : KoinTest {

    abstract val repository: BaseRepository

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(setModule, testModule)
    }

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    fun generateByteArray(size: Int): ByteArray {
        return rnd.nextBytes(size = size)
    }

    private val testRepository by inject<TestRepository>()

    protected suspend fun generateTestModel(): TestModel {
        return testRepository.createModel()
    }

    protected suspend fun generateTestDTO(): aleksandr.fedotkin.set.protocol.core.Test {
        return testRepository.createDTO()
    }

    protected val testMapper by lazy { testRepository.mapper }

    @Test
    fun test() {
        repository::class.functions.filter { it.hasAnnotation<RepositoryFunction>() }
            .forEach { function ->
                val defaultValues =
                    clazz.functions.filter { it.findAnnotation<DefaultValue>()?.name == function.name }
                        .map { it.call(this) }
                val values = when {
                    clazz.hasAnnotation<After>() -> {
                        clazz.functions.filter { it.findAnnotation<TestFunction>()?.name == function.name }
                            .zip(defaultValues)
                            .map { (f, defaultValue) ->
                                function.call(
                                    repository,
                                    f.call(this, defaultValue)
                                ) to defaultValue
                            }
                    }

                    clazz.hasAnnotation<Before>() -> {
                        clazz.functions.filter { it.findAnnotation<TestFunction>()?.name == function.name }
                            .zip(defaultValues).map { (f, defaultValue) ->
                                f.call(clazz, function.call(repository, defaultValue), defaultValue) to defaultValue
                            }
                    }

                    else -> emptyList()
                }

                if (values.isNotEmpty()) {
                    println("${repository::class.simpleName} -> ${function.name}")
                }
                values.forEach { (value, defaultValue) ->
                    assertEquals(expected = value, actual = defaultValue)
                }
            }
    }

    abstract val clazz: KClass<*>

    companion object {
        private val rnd = Random
    }
}