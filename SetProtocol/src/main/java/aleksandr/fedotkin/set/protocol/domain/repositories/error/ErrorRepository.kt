package aleksandr.fedotkin.set.protocol.domain.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.security.PrivateKey
import java.security.PublicKey

interface ErrorRepository<T: Model, R: DTO>: BaseSetRepository<ErrorModel<T>, Error<R>> {

    suspend fun verifyError(
        json: String,
    ): Boolean

    suspend fun createErrorMessage(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        privateKey: PrivateKey,
        messageWrapperModel: MessageWrapperModel<T>
    ): String
}
