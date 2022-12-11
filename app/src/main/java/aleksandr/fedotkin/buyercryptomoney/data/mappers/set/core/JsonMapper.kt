package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

class JsonMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

    fun <T> objectToByteArray(serializer: KSerializer<T>, data: T): ByteArray {
        return byteArrayMapper.map(string = objectToString(serializer = serializer, data = data))
    }

    fun <T> objectToString(serializer: KSerializer<T>, data: T): String {
        return Json.encodeToString(value = data, serializer = serializer)
    }

    fun <T> byteArrayToObject(deserializer: KSerializer<T>, data: ByteArray): T {
        return Json.decodeFromString(string = byteArrayMapper.map(byteArray = data), deserializer = deserializer)
    }

    fun <T> stringToObject(deserializer: KSerializer<T>, data: String): T {
        return Json.decodeFromString(string = data, deserializer = deserializer)
    }
}