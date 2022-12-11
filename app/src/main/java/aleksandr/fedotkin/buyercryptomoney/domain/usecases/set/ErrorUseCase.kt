package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.network.NetworkAPI
import aleksandr.fedotkin.buyercryptomoney.domain.common.SetError
import aleksandr.fedotkin.buyercryptomoney.domain.common.SignatureError
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

class ErrorUseCase(
    private val errorRepository: ErrorRepository,
    private val networkAPI: NetworkAPI,
    private val messageWrapperUseCase: MessageWrapperUseCase,
    private val messageWrapperRepository: MessageWrapperRepository
) : BaseUseCase() {

    suspend fun <T, R> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ) {
        val verify = errorRepository.checkError(error = error, map = map, serializer = serializer)
        if (verify != null && verify == false) {
            throw SignatureError()
        } else {
            throw SetError()
        }
    }

    suspend fun <T, R> sendError(
        messageWrapperModel: MessageWrapperModel<T>,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ) {
        val messageWrapper = messageWrapperRepository.convertToDTO(
            messageWrapperModel = messageWrapperModel,
            map = map
        )
        val error = createError(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            messageModel = messageWrapperModel.messageModel,
            publicKey = publicKey,
            errorCode = errorCode,
            map = map,
            serializer = serializer,
            privateKey = privateKey
        )
        val errorMessageWrapper =
            messageWrapperUseCase.changeMessage(message = error, messageWrapper = messageWrapper)
        val errorMessageWrapperJson = messageWrapperRepository.messageWrapperToJson(
            messageWrapper = errorMessageWrapper,
            serializer = Error.serializer(serializer)
        )
        networkAPI.sendError(messageWrapperJson = errorMessageWrapperJson)
    }

    suspend fun <T, R> sendError(
        messageWrapperModel: MessageWrapperModel<T>,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ) {
        sendError(
            messageWrapperModel = messageWrapperModel,
            publicKey = certificate.publicKey,
            errorCode = errorCode,
            map = map,
            serializer = serializer,
            privateKey = privateKey
        )
    }

    suspend fun <T, R> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R> {
        return errorRepository.createError(
            messageHeaderModel = messageHeaderModel,
            messageModel = messageModel,
            errorCode = errorCode,
            certificate = certificate,
            map = map,
            serializer = serializer,
            privateKey = privateKey
        )
    }

    suspend fun <T, R> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R> {
        return errorRepository.createError(
            messageHeaderModel = messageHeaderModel,
            messageModel = messageModel,
            errorCode = errorCode,
            publicKey = publicKey,
            map = map,
            serializer = serializer,
            privateKey = privateKey
        )
    }
}
