package aleksandr.fedotkin.set.protocol.domain.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import java.security.PublicKey

interface ErrorTBSRepository<T: Model, R: DTO>: BaseSetRepository<ErrorTBSModel<T>, ErrorTBS<R>> {

    suspend fun create(publicKey: PublicKey, errorCode: ErrorCode, messageHeader: MessageHeaderModel, message: T): ErrorTBSModel<T>

    suspend fun decodePublicKey(errorTBSModel: ErrorTBSModel<T>): PublicKey
}