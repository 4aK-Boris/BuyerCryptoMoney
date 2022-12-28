package aleksandr.fedotkin.set.protocol.core.usecase

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.core.exception.SetExternalException
import aleksandr.fedotkin.set.protocol.core.exception.SetInternalException
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

abstract class BaseSetUseCase<T : Model, R : DTO>(private val messageWrapperRepository: MessageWrapperRepository<T, R>) : BaseUseCase<T, R> {

    abstract suspend fun sendError(json: String)

    suspend fun <T : Any> safeCall(call: suspend CoroutineScope.() -> T): T = try {
        coroutineScope {
            call()
        }
    } catch (e: SetExternalException) {
        sendError(
            createErrorMessage(
                errorCode = e.errorCode,
                publicKey = publicKey,
                privateKey = privateKey,
            )
        )
        throw SetInternalException(errorCode = e.errorCode)
    }

    private val errorRepository by inject<ErrorRepository<T, R>> { parametersOf(repository.mapper) }

    abstract var messageWrapperModel: MessageWrapperModel<T>

    suspend fun convertFromString(json: String): MessageWrapperModel<T> {
        return safeConvertToString(json = json)
    }

    private suspend fun safeConvertToString(json: String): MessageWrapperModel<T> {
        try {
            return messageWrapperRepository.jsonToMessageWrapperModel(json = json)
        } catch (e: Exception) {
            if (errorRepository.verifyError(json = json)) {
                throw SetInternalException(errorCode = ErrorCode.DecodingFailure)
            } else {
                throw SetInternalException(errorCode = ErrorCode.SignatureFailure)
            }
        }
    }
    private suspend fun createErrorMessage(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        privateKey: PrivateKey
    ): String {
        return errorRepository.createErrorMessage(
            errorCode = errorCode,
            publicKey = publicKey,
            privateKey = privateKey,
            messageWrapperModel = messageWrapperModel
        )
    }

    protected val json: String
        get() {
            return runBlocking {
                messageWrapperRepository.messageWrapperModelToJson(messageWrapperModel = messageWrapperModel)
            }
        }


    suspend fun <S: Model> convertToMessageWrapperModelS(model: S): MessageWrapperModel<S> {
        return messageWrapperRepository.changeMessageModelOnOther(
            messageModel = model,
            messageWrapperModel = messageWrapperModel
        )
    }

    suspend fun <S: Model> convertToMessageWrapperModel(messageWrapperModel: MessageWrapperModel<S>, model: T) {
        this.messageWrapperModel = messageWrapperRepository.changeMessageModel(
            messageModel = model,
            messageWrapperModel = messageWrapperModel
        )
    }

    fun checkRRPID(rrpid: BigInteger) {
        if (messageWrapperModel.messageHeaderModel.rrpId != rrpid) {
            throw SetExternalException(errorCode = ErrorCode.UnknownXID)
        }
    }

    protected suspend fun createMessageWrapperModel(model: T): MessageWrapperModel<T> {
        return messageWrapperRepository.create(rrpid = rrpid, message = model)
    }
}