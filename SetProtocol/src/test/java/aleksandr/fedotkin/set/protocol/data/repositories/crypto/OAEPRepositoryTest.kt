package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep.OAEPModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEPRepository
import javax.crypto.SecretKey
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import kotlin.test.assertEquals

class OAEPRepositoryTest : BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<OAEPRepository>()

    private val keyRepository by inject<KeyRepository>()

    fun create(secretKey: SecretKey = keyRepository.generateSecretKey()): OAEPModel {
        return OAEPModel(secretKey = secretKey)
    }

    @Test
    fun testCreate() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val model = create(secretKey = secretKey)
        assertEquals(expected = model.secretKey, actual = secretKey)
    }

    @Test
    fun testEncryptAndDecrypt() = runBlocking {
        val secretKey = keyRepository.generateSecretKey()
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val model = create(secretKey = secretKey)
        val encryptModel = repository.encrypt(model = model, publicKey = publicKey)
        val decryptModel = repository.decrypt(data = encryptModel, privateKey = privateKey)
        assertEquals(expected = decryptModel.secretKey, actual = secretKey)
    }
}