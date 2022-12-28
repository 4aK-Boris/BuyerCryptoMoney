package aleksandr.fedotkin.set.protocol.domain.repositories.general

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.general.MessageWrapper
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import java.math.BigInteger

interface MessageWrapperRepository<T : Model, R : DTO> :
    BaseSetRepository<MessageWrapperModel<T>, MessageWrapper<R>> {

    suspend fun create(rrpid: BigInteger, message: T): MessageWrapperModel<T>

    suspend fun messageWrapperModelToJson(
        messageWrapperModel: MessageWrapperModel<T>,
    ): String

    suspend fun jsonToMessageWrapperModel(
        json: String,
    ): MessageWrapperModel<T>

    suspend fun <S: Model> changeRRPID(
        rrpid: BigInteger,
        messageWrapperModel: MessageWrapperModel<S>,
    ): MessageWrapperModel<S>

    suspend fun <S: Model> changeMessageModelOnOther(
        messageModel: S,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<S>

    suspend fun changeMessageModel(
        messageModel: T,
        messageWrapperModel: MessageWrapperModel<*>
    ): MessageWrapperModel<T>

    suspend fun changeMessageModel(
        rrpid: BigInteger,
        messageModel: T,
        messageWrapperModel: MessageWrapperModel<*>,
    ): MessageWrapperModel<T>

    suspend fun <S: Model> changeMessageModelOnOther(
        rrpid: BigInteger,
        messageModel: S,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<S>
}
