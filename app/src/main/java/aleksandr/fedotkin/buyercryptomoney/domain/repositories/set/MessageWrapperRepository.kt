package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import java.math.BigInteger

interface MessageWrapperRepository {

    suspend fun <T, R> changeMessage(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>
    ): MessageWrapperModel<R>

    suspend fun <T, R> changeMessage(
        messageModel: R,
        messageWrapperModel: MessageWrapperModel<T>,
        rrpid: BigInteger
    ): MessageWrapperModel<R>
}