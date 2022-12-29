package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper
import java.math.BigInteger

class BigIntegerMapper: CoreMapper<BigInteger, String> {

    override fun map(value: BigInteger): String {
        return value.toString()
    }

    override fun reverseMap(value: String): BigInteger {
        return value.toBigInteger()
    }
}