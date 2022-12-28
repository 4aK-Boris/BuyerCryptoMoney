package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.Test
import aleksandr.fedotkin.set.protocol.core.TestModel
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.SignedErrorRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SignedErrorRepositoryTest: BaseTestRepository() {

    override val clazz = this::class

    override val repository by inject<SignedErrorRepository<TestModel, Test>> { parametersOf(testMapper) }

    private val errorTBSRepositoryTest by inject<ErrorTBSRepositoryTest>()
    private val keyRepository by inject<KeyRepository>()

    @org.junit.Test
    fun testSignature() = runBlocking {
        val (publicKey, privateKey) = keyRepository.generatePairKey()
        val errorTBSModel = errorTBSRepositoryTest.create()
        val model = repository.createSignature(errorTBSModel = errorTBSModel, privateKey = privateKey)
        val verify = repository.verify(errorTBSModel = errorTBSModel, publicKey = publicKey, signature = model)
        assertTrue(actual = verify)
        val newErrorTBSModel = errorTBSModel.copy(errorCode = ErrorCode.SignatureFailure)
        val secondVerify = repository.verify(errorTBSModel = newErrorTBSModel, publicKey = publicKey, signature = model)
        assertFalse(actual = secondVerify)
    }
}