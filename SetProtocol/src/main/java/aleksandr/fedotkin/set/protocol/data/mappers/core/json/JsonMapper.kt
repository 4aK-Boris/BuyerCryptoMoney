package aleksandr.fedotkin.set.protocol.data.mappers.core.json

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import kotlinx.serialization.json.Json

class JsonMapper <R: DTO>(
    private val mapper: SetMapper<Model, R>
): CoreMapper<R, String> {

    override fun map(value: R): String {
        return Json.encodeToString(value = value, serializer = mapper.serializer)
    }

    override fun reverseMap(value: String): R {
        return Json.decodeFromString(string = value, deserializer = mapper.serializer)
    }
}