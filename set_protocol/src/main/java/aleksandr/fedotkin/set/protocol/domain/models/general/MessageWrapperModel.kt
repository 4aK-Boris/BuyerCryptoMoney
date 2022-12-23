package aleksandr.fedotkin.set.protocol.domain.models.general

import aleksandr.fedotkin.set.protocol.domain.models.Model

data class MessageWrapperModel<T>(
    val messageHeaderModel: MessageHeaderModel,
    val messageModel: T,
    val mWExtension: String?
): Model