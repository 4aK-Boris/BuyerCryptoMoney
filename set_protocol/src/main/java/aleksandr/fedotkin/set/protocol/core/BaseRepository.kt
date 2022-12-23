package aleksandr.fedotkin.set.protocol.core

import java.math.BigInteger
import kotlin.random.Random

abstract class BaseRepository {

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}