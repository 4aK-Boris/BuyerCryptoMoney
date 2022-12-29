package aleksandr.fedotkin.set.protocol.domain.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.UnsignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import java.security.PublicKey

interface UnsignedErrorRepository<T : Model, R : DTO> :
    BaseSetRepository<UnsignedErrorModel<T>, UnsignedError<R>> {

    suspend fun create(
        publicKey: PublicKey,
        errorCode: ErrorCode,
        messageHeader: MessageHeaderModel,
        message: T
    ): UnsignedErrorModel<T>
}