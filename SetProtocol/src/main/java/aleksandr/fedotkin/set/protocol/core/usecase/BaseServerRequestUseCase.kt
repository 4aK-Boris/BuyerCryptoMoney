package aleksandr.fedotkin.set.protocol.core.usecase

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository

abstract class BaseServerRequestUseCase<T : Model, R : DTO>(messageWrapperRepository: MessageWrapperRepository<T, R>) :
    BaseSetUseCase<T, R>(messageWrapperRepository = messageWrapperRepository) {

    abstract var sendMessage: suspend (String) -> Unit

    override suspend fun sendError(json: String) {
        sendMessage(json)
    }
}