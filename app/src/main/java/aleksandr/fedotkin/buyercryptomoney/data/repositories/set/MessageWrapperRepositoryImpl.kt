package aleksandr.fedotkin.buyercryptomoney.data.repositories.set

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageWrapperMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageIDModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import java.time.LocalDateTime
import kotlinx.serialization.KSerializer
import kotlin.random.Random

class MessageWrapperRepositoryImpl(
    private val errorRepository: ErrorRepository,
    private val jsonMapper: JsonMapper,
    private val messageWrapperMapper: MessageWrapperMapper
) : MessageWrapperRepository {

    override suspend fun <T> messageWrapperToJson(
        messageWrapper: MessageWrapper<T>,
        serializer: KSerializer<T>
    ): String {
        return jsonMapper.objectToString(
            data = messageWrapper,
            serializer = MessageWrapper.serializer(serializer)
        )
    }

    override suspend fun <T> jsonToMessageWrapper(
        messageWrapperJson: String,
        serializer: KSerializer<T>
    ): MessageWrapper<T> {
        return jsonMapper.stringToObject(
            data = messageWrapperJson,
            deserializer = MessageWrapper.serializer(serializer)
        )
    }

    override suspend fun <T, R> changeMessage(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<R> {
        return MessageWrapperModel(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun <T, R> changeMessage(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger
    ): MessageWrapperModel<R> {
        val messageHeaderModel = changeMessageHeader(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            rrpid = rrpid
        )
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun <T> createMessageWrapperModel(
        messageModel: T,
        rrpid: BigInteger
    ): MessageWrapperModel<T> {
        return MessageWrapperModel(
            mWExtension = null,
            messageModel = messageModel,
            messageHeaderModel = createMessageHeaderModel(rrpid = rrpid)
        )
    }

    private fun createMessageHeaderModel(rrpid: BigInteger): MessageHeaderModel {
        return MessageHeaderModel(
            rrpId = rrpid, messageIDModel = MessageIDModel(
                lIdC = generateNewNumber(),
                lIdM = generateNewNumber(),
                xId = generateNewNumber()
            )
        )
    }

    override suspend fun <T, R> createErrorMessageWrapper(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<T>> {
        val model = createErrorMessageWrapperModel(
            messageWrapper = messageWrapper,
            errorCode = errorCode,
            map = map,
            mapReverse = mapReverse,
            publicKey = publicKey,
            serializer = serializer,
            privateKey = privateKey
        )
        return messageWrapperMapper.map(
            model = model,
            map = mapReverse,
            errorMap = errorRepository::convertToDTO
        )
    }

    override suspend fun <T, R> createErrorMessageWrapper(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<T>> {
        return createErrorMessageWrapper(
            messageWrapper = messageWrapper,
            errorCode = errorCode,
            map = map,
            mapReverse = mapReverse,
            publicKey = certificate.publicKey,
            serializer = serializer,
            privateKey = privateKey
        )
    }

    override suspend fun <T, R> createErrorMessageWrapperModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapperModel<ErrorModel<R>> {
        return createErrorMessageWrapperModel(
            messageWrapper = messageWrapper,
            map = map,
            mapReverse = mapReverse,
            errorCode = errorCode,
            publicKey = certificate.publicKey,
            serializer = serializer,
            privateKey = privateKey
        )
    }

    override suspend fun <T, R> createErrorMessageWrapperModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R,
        mapReverse: (R) -> T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        serializer: KSerializer<T>,
        privateKey: PrivateKey
    ): MessageWrapperModel<ErrorModel<R>> {
        val messageWrapperModel = convertToModel(messageWrapper = messageWrapper, map = map)
        return changeMessage(
            messageWrapperModel = messageWrapperModel,
            messageModel = errorRepository.createErrorModel(
                messageModel = messageWrapperModel.messageModel,
                messageHeaderModel = messageWrapperModel.messageHeaderModel,
                errorCode = errorCode,
                publicKey = publicKey,
                map = mapReverse,
                serializer = serializer,
                privateKey = privateKey
            )
        )
    }

    override suspend fun <T, R> convertToModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R
    ): MessageWrapperModel<R> {
        return messageWrapperMapper.map(dto = messageWrapper, map = map)
    }

    override suspend fun <T, R> convertToDTO(
        messageWrapper: MessageWrapperModel<T>,
        map: (T) -> R
    ): MessageWrapper<R> {
        return messageWrapperMapper.map(model = messageWrapper, map = map)
    }

    private fun changeMessageHeader(
        messageHeaderModel: MessageHeaderModel,
        rrpid: BigInteger
    ): MessageHeaderModel {
        return messageHeaderModel.copy(rrpId = rrpid, date = LocalDateTime.now())
    }

    override fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}
