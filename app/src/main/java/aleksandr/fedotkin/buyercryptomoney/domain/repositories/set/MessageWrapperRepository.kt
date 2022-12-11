package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface MessageWrapperRepository {

    suspend fun <T> messageWrapperToJson(
        messageWrapper: MessageWrapper<T>,
        serializer: KSerializer<T>
    ): String

    suspend fun <T> jsonToMessageWrapper(
        messageWrapperJson: String,
        serializer: KSerializer<T>
    ): MessageWrapper<T>

    suspend fun <T, R> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>
    ): MessageWrapper<R>

    suspend fun <T, R> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>,
        rrpid: BigInteger
    ): MessageWrapper<R>

    suspend fun <T, R> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<R>

    suspend fun <T, R> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger
    ): MessageWrapperModel<R>

    suspend fun <T> createMessageWrapperModel(
        messageModel: T,
        rrpid: BigInteger = generateNewNumber()
    ): MessageWrapperModel<T>

    suspend fun <T, R> createErrorMessageWrapper(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<T>>

    suspend fun <T, R> createErrorMessageWrapper(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<T>>

    suspend fun <T, R> createErrorMessageWrapperModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapperModel<ErrorModel<R>>

    suspend fun <T, R> createErrorMessageWrapperModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapperModel<ErrorModel<R>>

    suspend fun <T, R> convertToModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R
    ): MessageWrapperModel<R>

    suspend fun <T, R> convertToDTO(
        messageWrapperModel: MessageWrapperModel<T>,
        map: (T) -> R
    ): MessageWrapper<R>

    fun generateNewNumber(): BigInteger
}
