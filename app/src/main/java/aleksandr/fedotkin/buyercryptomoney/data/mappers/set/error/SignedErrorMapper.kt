package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.SignedError
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.SignedErrorModel

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
