package aleksandr.fedotkin.set.protocol.data.repositories.general

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageIDRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertTrue

class MessageIDRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<MessageIDRepository>()

    @Test
    fun testCreate() = runBlocking {
        val model = repository.create()
        assertTrue(actual = model.xId != null,)
        assertTrue(actual = model.lIdC != null)
        assertTrue(actual = model.lIdM != null)
    }
}