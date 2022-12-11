package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core

import io.ktor.util.decodeBase64Bytes
import io.ktor.util.encodeBase64

class ByteArrayMapper {

    @JvmName("map_notnull_bytearray")
    fun map(byteArray: ByteArray): String {
        return byteArray.encodeBase64()
    }

    @JvmName("map_notnull_string")
    fun map(string: String): ByteArray {
        return string.decodeBase64Bytes()
    }

    @JvmName("map_nullable_bytearray")
    fun map(byteArray: ByteArray?): String? {
        return byteArray?.encodeBase64()
    }

    @JvmName("map_nullable_string")
    fun map(string: String?): ByteArray? {
        return string?.decodeBase64Bytes()
    }
}