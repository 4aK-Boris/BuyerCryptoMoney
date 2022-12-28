package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel


data class ErrorMsgModel<T: Model>(
    val messageHeader: MessageHeaderModel,
    val badWrapper: T
): Model
