package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.UnknownRRPID
import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.math.BigInteger

abstract class ResponseUseCase<T : Model, R : DTO> : ProcessingErrorUseCase<T, R>() {

    suspend fun checkMessageWrapperAndConvertToModel(messageWrapperJson: String): MessageWrapperModel<T> {
        return convertToModel(messageWrapper = checkMessageWrapperAndConvertToDTO(messageWrapperJson = messageWrapperJson))
    }

    suspend fun checkRRPID(
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger,
        messageRRPID: BigInteger
    ) {
        if (messageWrapperModel.messageHeaderModel.rrpId != rrpid || rrpid != messageRRPID) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.UnknownXID,
            )
            throw UnknownRRPID()
        }
    }

    private suspend fun checkMessageWrapperAndConvertToDTO(messageWrapperJson: String): MessageWrapper<R> {
        try {
            return jsonToMessageWrapper(messageWrapperJson = messageWrapperJson)
        } catch (e: Exception) {
            checkError(error = jsonToErrorMessageWrapper(messageWrapperJson = messageWrapperJson).message)
            throw Exception()
        }
    }
}