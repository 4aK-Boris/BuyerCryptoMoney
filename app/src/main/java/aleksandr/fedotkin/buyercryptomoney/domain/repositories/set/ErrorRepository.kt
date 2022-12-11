package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorTBSModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface ErrorRepository {

    suspend fun <T, R> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ): ErrorModel<R>

    suspend fun <T, R> createErrorModel(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T>

    suspend fun <T, R> createErrorModel(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T>

    suspend fun <T, R> createErrorMessageWrapper(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<R>>
}
