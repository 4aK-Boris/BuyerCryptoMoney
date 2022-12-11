package aleksandr.fedotkin.buyercryptomoney.domain.model.set.general

data class MessageWrapperModel<T>(
    val messageHeaderModel: MessageHeaderModel,
    val messageModel: T,
    val mWExtension: String?
)