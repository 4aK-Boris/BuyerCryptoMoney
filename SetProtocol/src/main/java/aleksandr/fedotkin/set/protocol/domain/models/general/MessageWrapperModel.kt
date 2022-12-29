package aleksandr.fedotkin.set.protocol.domain.models.general

import aleksandr.fedotkin.set.protocol.core.Model

data class MessageWrapperModel<T: Model>(
    val messageHeaderModel: MessageHeaderModel,
    val messageModel: T,
    val mWExtension: String?
): Model