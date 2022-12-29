package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import java.math.BigInteger
import java.security.PublicKey
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class RegFormReqRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<RegFormReqRepository>()

    private val keyRepository by inject<KeyRepository>()

    suspend fun create(
        rrpid: BigInteger = generateNewNumber(),
        lidEE: BigInteger = generateNewNumber(),
        lidCA: BigInteger = generateNewNumber(),
        number: String = NUMBER,
        publicKey: PublicKey
    ): RegFormReqModel {
        return repository.create(rrpid, lidEE, lidCA, number, publicKey)
    }

    @Test
    fun testCreateAndDecrypt() = runBlocking {
        val rrpid = generateNewNumber()
        val lidEE = generateNewNumber()
        val lidCA = generateNewNumber()
        val number = "1248784564577895"
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = create(rrpid, lidEE, lidCA, number, publicKey)
        val (regFromReqDataModel, panOnlyModel) = repository.decryptAndCheck(
            rrpid = rrpid,
            privateKey = privateKey,
            model = model
        )
        assertEquals(expected = panOnlyModel.pan.toString(), actual = number)
        assertEquals(expected = regFromReqDataModel.lidEE, actual = lidEE)
        assertEquals(expected = regFromReqDataModel.lidCA, actual = lidCA)
        assertEquals(expected = regFromReqDataModel.language, actual = Language.RUSSIAN)
        assertEquals(expected = regFromReqDataModel.requestType, actual = RequestType.SIGNATURE)
    }

    companion object {
        private const val NUMBER = "1111222233334444"
    }
}