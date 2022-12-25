package aleksandr.fedotkin.set.protocol.data.mappers.core

import java.math.BigInteger

class BigIntegerMapper {

    @JvmName("map_notnull_number")
    fun map(number: BigInteger): String {
        return number.toString()
    }

    @JvmName("map_nullable_number")
    fun map(number: BigInteger?): String? {
        return number?.toString()
    }

    @JvmName("map_notnull_string")
    fun map(string: String): BigInteger {
        return string.toBigInteger()
    }

    @JvmName("map_nullable_string")
    fun map(string: String?): BigInteger? {
        return string?.toBigInteger()
    }
}