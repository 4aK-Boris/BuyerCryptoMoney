package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.error.UnsignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import kotlinx.serialization.KSerializer

class UnsignedErrorMapper<T: Model, R: DTO>(
    private val errorTBSMapper: ErrorTBSMapper<T, R>,
    private val mapper: SetMapper<T, R>
): SetMapper<UnsignedErrorModel<T>, UnsignedError<R>> {

    override val serializer: KSerializer<UnsignedError<R>>
        get() = UnsignedError.serializer(mapper.serializer)


    override fun map(value: UnsignedErrorModel<T>): UnsignedError<R> {
        return UnsignedError(errorTBS = errorTBSMapper.map(value = value.errorTBS))
    }
    override fun reverseMap(value: UnsignedError<R>): UnsignedErrorModel<T> {
        return UnsignedErrorModel(errorTBS = errorTBSMapper.reverseMap(value = value.errorTBS))
    }
}
