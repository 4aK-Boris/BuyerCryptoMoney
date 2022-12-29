package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import java.lang.NumberFormatException
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PANOnlyRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<PANOnlyRepository>()

    suspend fun create(number: String = NUMBER): PANOnlyModel {
        return repository.create(number = number)
    }

    @Test
    fun testCreate(): Unit = runBlocking {
        val number = "8458952458954578"
        val model = create(number = number)
        assertEquals(expected = model.pan.toString(), actual = number)
        assertFailsWith(exceptionClass = NumberFormatException::class) {
            create(number = "dwadwadwadwadwadwa")
        }
    }

    companion object {
        private const val NUMBER = "1111222233334444"
    }
}