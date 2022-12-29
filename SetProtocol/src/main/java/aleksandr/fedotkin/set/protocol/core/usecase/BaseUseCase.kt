package aleksandr.fedotkin.set.protocol.core.usecase

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import org.koin.core.component.KoinComponent
import kotlin.random.Random

interface BaseUseCase<T : Model, R : DTO> : KoinComponent {

    val publicKey: PublicKey

    val privateKey: PrivateKey

    var rrpid: BigInteger

    val repository: BaseSetRepository<T, R>

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(20))
    }

    fun generateNewNumber(data: String): BigInteger {
        return BigInteger(data)
    }

    fun generateByteArray(size: Int): ByteArray {
        return rnd.nextBytes(size = size)
    }
    companion object {
        private val rnd = Random
    }
}