package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.data.dto.error.SignedError
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel

class SignedErrorMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(dto: SignedError): SignedErrorModel {
        return SignedErrorModel(signature = byteArrayMapper.map(string = dto.signature))
    }

    fun map(model: SignedErrorModel): SignedError {
        return SignedError(signature = byteArrayMapper.map(byteArray = model.signature))
    }
}
