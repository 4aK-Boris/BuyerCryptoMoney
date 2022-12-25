package aleksandr.fedotkin.set.protocol.domain.repositories

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.math.BigInteger
import kotlinx.serialization.KSerializer

interface MessageWrapperRepository {

    suspend fun <T: DTO> messageWrapperToJson(
        messageWrapper: MessageWrapper<T>,
        serializer: KSerializer<T>
    ): String

    suspend fun <T: DTO> jsonToMessageWrapper(
        messageWrapperJson: String,
        serializer: KSerializer<T>
    ): MessageWrapper<T>

    suspend fun <T: DTO, R: DTO> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>
    ): MessageWrapper<R>

    suspend fun <T: DTO, R: DTO> changeMessage(
        message: R,
        messageWrapper: MessageWrapper<T>,
        rrpid: BigInteger
    ): MessageWrapper<R>

    suspend fun <T: Model, R: Model> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<R>

    suspend fun <T: Model, R: Model> changeMessageModel(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger
    ): MessageWrapperModel<R>

    suspend fun <T: Model> createMessageWrapperModel(
        messageModel: T,
        rrpid: BigInteger
    ): MessageWrapperModel<T>

    suspend fun <T : DTO, R : Model> convertToModel(
        messageWrapper: MessageWrapper<T>,
        map: (T) -> R
    ): MessageWrapperModel<R>

    suspend fun <T : Model, R : DTO> convertToDTO(
        messageWrapperModel: MessageWrapperModel<T>,
        map: (T) -> R
    ): MessageWrapper<R>
}
