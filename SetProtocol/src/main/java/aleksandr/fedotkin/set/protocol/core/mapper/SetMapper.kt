package aleksandr.fedotkin.set.protocol.core.mapper

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import kotlinx.serialization.KSerializer

interface SetMapper<T : Model, R : DTO>: BaseMapper<T, R> {

    val serializer: KSerializer<R>
}