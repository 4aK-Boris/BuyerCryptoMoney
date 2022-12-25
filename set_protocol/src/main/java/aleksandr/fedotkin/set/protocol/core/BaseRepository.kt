package aleksandr.fedotkin.set.protocol.core

import java.math.BigInteger
import kotlin.random.Random

abstract class BaseRepository {

    fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    fun generateNewNumber(data: String): BigInteger {
        return BigInteger(data)
    }

    companion object {
        private val rnd = Random
    }
}