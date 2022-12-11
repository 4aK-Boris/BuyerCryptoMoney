package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.SignedError
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.SignedErrorModel

class SignedErrorMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(signedError: SignedError): SignedErrorModel {
        return SignedErrorModel(signature = byteArrayMapper.map(string = signedError.signature))
    }

    fun map(signedErrorModel: SignedErrorModel): SignedError {
        return SignedError(signature = byteArrayMapper.map(byteArray = signedErrorModel.signature))
    }
}