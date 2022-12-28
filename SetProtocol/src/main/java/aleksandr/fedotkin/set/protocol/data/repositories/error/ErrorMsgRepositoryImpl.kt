package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMsgMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorMsgRepository

class ErrorMsgRepositoryImpl<T: Model, R: DTO>(
    override val mapper: ErrorMsgMapper<T, R>
): ErrorMsgRepository<T, R> {

    override suspend fun create(message: T, messageHeader: MessageHeaderModel): ErrorMsgModel<T> {
        return ErrorMsgModel(messageHeader = messageHeader, badWrapper = message)
    }
}