package aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res

import aleksandr.fedotkin.set.protocol.core.di.certificate.Certificate
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.req.CardCInitReqModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.card.c.init.res.CardCInitResTBSModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CardCInitResTBSRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<CardCInitResTBSRepository>()

    private val cardCInitReqRepositoryTest by inject<CardCInitReqRepositoryTest>()
    private val certificate by inject<Certificate>(qualifier = named(CertificateQualifiers.CCA))

    suspend fun create(cardCInitReqModel: CardCInitReqModel): CardCInitResTBSModel {
        return repository.create(model = cardCInitReqModel)
    }


    @Test
    fun testCreate() = runBlocking {
        val rrpid = generateNewNumber()
        val cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid = rrpid)
        val model = create(cardCInitReqModel = cardCInitReqModel)
        assertEquals(expected = model.rrpID, actual = rrpid)
        assertContentEquals(expected = model.thumbs, cardCInitReqModel.thumbs)
        assertEquals(expected = model.lidEE, actual = cardCInitReqModel.lidEE)
        assertEquals(expected = model.challEE, actual = cardCInitReqModel.challEE)
        assertContentEquals(expected = model.caeThumb, certificate.x509Certificate.encoded)
    }

    @Test
    fun testSignature() = runBlocking {
        val rrpid = generateNewNumber()
        val cardCInitReqModel = cardCInitReqRepositoryTest.create(rrpid = rrpid)
        val model = create(cardCInitReqModel = cardCInitReqModel)
        val signature = repository.createSignature(model = model)
        val verify = repository.verifySignature(model = model, signature = signature)
        assertTrue(actual = verify)

        var errorModel = changeRRPID(model = model)
        var error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeLIDEE(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeChallEE(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)

        errorModel = changeThumbs(model = model)
        error = repository.verifySignature(model = errorModel, signature = signature)
        assertFalse(actual = error)
    }

    private fun changeRRPID(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(rrpID = generateNewNumber())
    }

    private fun changeLIDEE(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(lidEE = generateNewNumber())
    }

    private fun changeChallEE(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(challEE = generateNewNumber())
    }

    private fun changeThumbs(model: CardCInitResTBSModel): CardCInitResTBSModel {
        return model.copy(thumbs = listOf(generateByteArray(size = 256)))
    }
}