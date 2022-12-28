package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper

class ByteArrayMapper: CoreMapper<String, ByteArray> {
    override fun map(value: String): ByteArray {
        return value.encodeToByteArray()
    }

    override fun reverseMap(value: ByteArray): String {
        return value.decodeToString()
    }
}