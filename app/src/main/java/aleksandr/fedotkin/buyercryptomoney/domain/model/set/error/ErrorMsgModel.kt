package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel

data class ErrorMsgModel<T>(
    val messageHeader: MessageHeaderModel,
    val badWrapper: T
)
