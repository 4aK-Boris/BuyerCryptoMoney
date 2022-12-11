package aleksandr.fedotkin.buyercryptomoney.data.repositories.set

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import java.math.BigInteger
import java.time.LocalDateTime

class MessageWrapperRepositoryImpl: MessageWrapperRepository {

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
        val messageHeaderModel = changeMessageHeader(messageHeaderModel = messageWrapperModel.messageHeaderModel, rrpid = rrpid)
        return MessageWrapperModel(
            messageHeaderModel = messageHeaderModel,
            mWExtension = messageWrapperModel.mWExtension,
            messageModel = messageModel
        )
    }

    private fun changeMessageHeader(messageHeaderModel: MessageHeaderModel, rrpid: BigInteger): MessageHeaderModel {
        return messageHeaderModel.copy(rrpId = rrpid, date = LocalDateTime.now())
    }
}