package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.data.network.SetNetworkAPI
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import org.koin.core.component.inject

abstract class ProcessingErrorUseCase<T : Model, R : DTO> : ErrorUseCase<T, R>() {

    protected val networkAPI by inject<SetNetworkAPI>()

    protected suspend fun sendError(messageWrapperModel: MessageWrapperModel<T>, errorCode: ErrorCode) {
        changeMessage(
            message = createError(messageWrapperModel = messageWrapperModel, errorCode = errorCode),
            messageWrapper = convertToDTO(messageWrapperModel = messageWrapperModel)
        ).let { errorMessageWrapper ->
            networkAPI.sendError(messageWrapperJson = errorMessageWrapperToJson(messageWrapper = errorMessageWrapper))
        }
    }
}