package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface ErrorRepository {

    suspend fun <T, R> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ): Boolean?

    suspend fun <T, R> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R>

    suspend fun <T, R> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R>

    suspend fun <T, R> createErrorModel(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        errorCode: ErrorCode,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T>

    suspend fun <T, R> createErrorModel(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T>

    fun <T, R> convertToModel(error: Error<T>, map: (T) -> R): ErrorModel<R>

    fun <T, R> convertToDTO(errorModel: ErrorModel<T>, map: (T) -> R): Error<R>
}
