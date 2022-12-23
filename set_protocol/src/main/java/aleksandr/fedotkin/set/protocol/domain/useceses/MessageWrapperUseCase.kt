package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.BaseUseCase
import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.MessageWrapperRepository
import java.math.BigInteger
import kotlinx.serialization.KSerializer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class MessageWrapperUseCase<T : Model, R : DTO> : KoinComponent, BaseUseCase {

    private val messageWrapperRepository by inject<MessageWrapperRepository>()

    abstract val convertToModel: (R) -> T

    abstract val convertToDTO: (T) -> R

    abstract val serializer: KSerializer<R>

    protected suspend fun jsonToMessageWrapper(messageWrapperJson: String): MessageWrapper<R> {
        return messageWrapperRepository.jsonToMessageWrapper(
            messageWrapperJson = messageWrapperJson,
            serializer = serializer
        )
    }

    protected suspend fun messageWrapperToJson(messageWrapper: MessageWrapper<R>): String {
        return messageWrapperRepository.messageWrapperToJson(
            messageWrapper = messageWrapper,
            serializer = serializer
        )
    }

    protected suspend fun jsonToErrorMessageWrapper(messageWrapperJson: String): MessageWrapper<Error<R>> {
        return messageWrapperRepository.jsonToMessageWrapper(
            messageWrapperJson = messageWrapperJson,
            serializer = Error.serializer(serializer)
        )
    }

    protected suspend fun errorMessageWrapperToJson(messageWrapper: MessageWrapper<Error<R>>): String {
        return messageWrapperRepository.messageWrapperToJson(
            messageWrapper = messageWrapper,
            serializer = Error.serializer(serializer)
        )
    }

    protected suspend fun convertToModel(messageWrapper: MessageWrapper<R>): MessageWrapperModel<T> {
        return messageWrapperRepository.convertToModel(
            messageWrapper = messageWrapper,
            map = convertToModel
        )
    }

    protected suspend fun convertToDTO(messageWrapperModel: MessageWrapperModel<T>): MessageWrapper<R> {
        return messageWrapperRepository.convertToDTO(
            messageWrapperModel = messageWrapperModel,
            map = convertToDTO
        )
    }

    protected suspend fun <M : Model, N : Model> changeNewMessageModel(
        messageModel: M,
        messageWrapperModel: MessageWrapperModel<N>
    ): MessageWrapperModel<M> {
        return messageWrapperRepository.changeMessageModel(
            messageModel = messageModel,
            messageWrapperModel = messageWrapperModel
        )
    }

    protected suspend fun <M : Model, N : Model> changeMessageModel(
        messageModel: M,
        messageWrapperModel: MessageWrapperModel<N>,
        rrpid: BigInteger
    ): MessageWrapperModel<M> {
        return messageWrapperRepository.changeMessageModel(
            messageModel = messageModel,
            messageWrapperModel = messageWrapperModel,
            rrpid = rrpid
        )
    }

    protected suspend fun <D : DTO, O : DTO> changeMessage(
        message: D,
        messageWrapper: MessageWrapper<O>
    ): MessageWrapper<D> {
        return messageWrapperRepository.changeMessage(
            message = message,
            messageWrapper = messageWrapper
        )
    }

    protected suspend fun createMessageWrapperModel(rrpid: BigInteger, messageModel: T): MessageWrapperModel<T> {
        return messageWrapperRepository.createMessageWrapperModel(rrpid = rrpid, messageModel = messageModel)
    }
}
