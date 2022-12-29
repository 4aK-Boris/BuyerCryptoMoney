package aleksandr.fedotkin.set.protocol.core.repository

import java.math.BigInteger
import kotlin.random.Random

interface BaseRepository {
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