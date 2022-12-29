package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.SetError
import aleksandr.fedotkin.set.protocol.core.SignatureError
import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.ErrorRepository
import java.security.PrivateKey
import java.security.PublicKey
import org.koin.core.component.inject

abstract class ErrorUseCase <T : Model, R : DTO> : MessageWrapperUseCase<T, R>() {

    private val errorRepository by inject<ErrorRepository>()
    private val publicKey by inject<PublicKey>()
    private val privateKey by inject<PrivateKey>()

    protected suspend fun checkError(
        error: Error<R>,
    ) {
        val verify = errorRepository.checkError(error = error, map = convertToModel, serializer = ErrorTBS.serializer(serializer))
        if (verify != null && verify == false) {
            throw SignatureError()
        } else {
            throw SetError()
        }
    }

    protected suspend fun createError(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
    ): Error<R> {
        return errorRepository.createError(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            messageModel = messageWrapperModel.messageModel,
            errorCode = errorCode,
            publicKey = publicKey,
            map = convertToDTO,
            serializer = serializer,
            privateKey = privateKey
        )
    }
}
