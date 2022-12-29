package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import java.math.BigInteger
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class CardCInitReqRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<CardCInitReqRepository>()

    suspend fun create(rrpid: BigInteger = generateNewNumber()): CardCInitReqModel {
        return repository.createCardCInitReqModel(rrpid = rrpid)
    }

    @Test
    fun testCreate() = runBlocking {
        val rrpid = generateNewNumber()
        val model = create(rrpid = rrpid)
        assertEquals(expected = model.rrpID, actual = rrpid)
        assertEquals(expected = model.thumbs, emptyList())
    }
}