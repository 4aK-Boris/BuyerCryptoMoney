package aleksandr.fedotkin.set.protocol.domain.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorMsg
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel

interface ErrorMsgRepository<T: Model, R: DTO>: BaseSetRepository<ErrorMsgModel<T>, ErrorMsg<R>> {

    suspend fun create(message: T, messageHeader: MessageHeaderModel): ErrorMsgModel<T>
}