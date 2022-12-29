package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper
import io.ktor.util.decodeBase64Bytes
import io.ktor.util.encodeBase64

class Base64Mapper: CoreMapper<ByteArray, String> {
    override fun map(value: ByteArray): String {
        return value.encodeBase64()
    }

    override fun reverseMap(value: String): ByteArray {
        return value.decodeBase64Bytes()
    }
}