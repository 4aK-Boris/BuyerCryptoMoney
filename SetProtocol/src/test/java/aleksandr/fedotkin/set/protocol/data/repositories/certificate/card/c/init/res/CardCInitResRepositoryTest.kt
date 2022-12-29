package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.di.certificate.Certificate
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CardCInitResRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<CardCInitResRepository>()

    private val cardCInitReqRepositoryTest by inject<CardCInitReqRepositoryTest>()
    private val certificate by inject<Certificate>(qualifier = named(CertificateQualifiers.CCA))

    suspend fun create(cardCInitReqModel: CardCInitReqModel): CardCInitResModel {
        return repository.create(cardCInitReqModel = cardCInitReqModel)
    }

    @Test
    fun testCreate() = runBlocking {
        val rrpid = generateNewNumber()
        val cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid = rrpid)
        val model = create(cardCInitReqModel = cardCInitReqModel)
        assertEquals(expected = model.cardCInitResTBS.rrpID, actual = rrpid)
        assertContentEquals(expected = model.cardCInitResTBS.thumbs, cardCInitReqModel.thumbs)
        assertEquals(expected = model.cardCInitResTBS.lidEE, actual = cardCInitReqModel.lidEE)
        assertEquals(expected = model.cardCInitResTBS.challEE, actual = cardCInitReqModel.challEE)
        assertContentEquals(expected = model.cardCInitResTBS.caeThumb, certificate.x509Certificate.encoded)
    }

    @Test
    fun testCheck(): Unit = runBlocking {
        val rrpid = generateNewNumber()
        val cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid = rrpid)
        val model = repository.create(cardCInitReqModel = cardCInitReqModel)
        repository.check(rrpid, cardCInitReqModel, model)
    }
}