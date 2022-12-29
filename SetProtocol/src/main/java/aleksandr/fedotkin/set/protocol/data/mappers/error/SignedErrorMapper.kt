package aleksandr.fedotkin.set.protocol.data.mappers.error

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.error.SignedError
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel
import kotlinx.serialization.KSerializer

class SignedErrorMapper(
    private val base64Mapper: Base64Mapper
): SetMapper<SignedErrorModel, SignedError> {

    override val serializer: KSerializer<SignedError>
        get() = SignedError.serializer()

    override fun map(value: SignedErrorModel): SignedError {
        return SignedError(signature = base64Mapper.map(value = value.signature))
    }

    override fun reverseMap(value: SignedError): SignedErrorModel {
        return SignedErrorModel(signature = base64Mapper.reverseMap(value = value.signature))
    }
}
