package aleksandr.fedotkin.set.protocol.data.repositories

import aleksandr.fedotkin.set.protocol.core.BaseRepository
import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageHeader
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageWrapperMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageIDModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.MessageWrapperRepository
import java.math.BigInteger
import java.time.LocalDateTime
import kotlinx.serialization.KSerializer

class MessageWrapperRepositoryImpl(
    private val jsonMapper: JsonMapper,
    private val messageWrapperMapper: MessageWrapperMapper,
    private val messageHeaderMapper: MessageHeaderMapper
) : MessageWrapperRepository, BaseRepository() {

    override suspend fun <T : DTO> messageWrapperToJson(
        messageWrapper: MessageWrapper<T>,
        serializer: KSerializer<T>
    ): String {
        return jsonMapper.objectToString(
            data = messageWrapper,
            serializer = MessageWrapper.serializer(serializer)
        )
    }

    override suspend fun <T : DTO> jsonToMessageWrapper(
        messageWrapperJson: String,
        serializer: KSerializer<T>
    ): MessageWrapper<T> {
        return jsonMapper.stringToObject(
            data = messageWrapperJson,
            deserializer = MessageWrapper.serializer(serializer)
        )
    }

    override suspend fun <T : DTO, R : DTO> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>
    ): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = messageWrapper.messageHeader,
            mWExtension = messageWrapper.mWExtension,
            message = message
        )
    }

    override suspend fun <T : DTO, R : DTO> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>,
        rrpid: BigInteger
    ): MessageWrapper<R> {
        return MessageWrapper(
            messageHeader = changeMessageHeader(
                rrpid = rrpid,
                messageHeader = messageWrapper.messageHeader
            ),
            mWExtension = messageWrapper.mWExtension,
            message = message
        )
    }

    override suspend fun <T : Model, R : Model> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<R> {
        return MessageWrapperModel(
            messageHeaderModel = messageWrapperModel.messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun <T : Model, R : Model> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger
    ): MessageWrapperModel<R> {
        return MessageWrapperModel(
            messageHeaderModel = changeMessageHeaderModel(
                messageHeaderModel = messageWrapperModel.messageHeaderModel,
                rrpid = rrpid
            ),
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    override suspend fun <T : Model> createMessageWrapperModel(
        messageModel: T,
        rrpid: BigInteger
    ): MessageWrapperModel<T> {
        return MessageWrapperModel(
            mWExtension = null,
            messageModel = messageModel,
            messageHeaderModel = createMessageHeaderModel(rrpid = rrpid)
        )
    }

    override suspend fun <T : DTO, R : Model> convertToModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R
    ): MessageWrapperModel<R> {
        return messageWrapperMapper.map(dto = messageWrapper, map = map)
    }

    override suspend fun <T : Model, R : DTO> convertToDTO(
        messageWrapperModel: MessageWrapperModel<T>,
        map: (T) -> R
    ): MessageWrapper<R> {
        return messageWrapperMapper.map(model = messageWrapperModel, map = map)
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

    private fun changeMessageHeaderModel(
        messageHeaderModel: MessageHeaderModel,
        rrpid: BigInteger
    ): MessageHeaderModel {
        return messageHeaderModel.copy(rrpId = rrpid, date = LocalDateTime.now())
    }

    private fun changeMessageHeader(
        messageHeader: MessageHeader,
        rrpid: BigInteger
    ): MessageHeader {
        return messageHeaderMapper.map(
            model = changeMessageHeaderModel(
                messageHeaderModel = messageHeaderMapper.map(
                    dto = messageHeader
                ), rrpid = rrpid
            )
        )
    }
}
