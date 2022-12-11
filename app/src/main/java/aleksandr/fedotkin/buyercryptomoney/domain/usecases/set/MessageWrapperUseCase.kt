package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import kotlinx.serialization.KSerializer

class MessageWrapperUseCase(
    private val messageWrapperRepository: MessageWrapperRepository,
    private val errorUseCase: ErrorUseCase
) : BaseUseCase() {

    suspend fun <T, R> checkMessageWrapperAndConvertToDTO(
        messageWrapperJson: String,
        serializer: KSerializer<T>,
        map: (T) -> R
    ): MessageWrapper<T> {
        try {
            return messageWrapperRepository.jsonToMessageWrapper(
                messageWrapperJson = messageWrapperJson,
                serializer = serializer
            )
        } catch (e: Exception) {
            messageWrapperRepository.jsonToMessageWrapper(
                messageWrapperJson = messageWrapperJson,
                serializer = Error.serializer(serializer)
            ).run {
                errorUseCase.checkError(
                    error = this.message,
                    map = map,
                    serializer = ErrorTBS.serializer(serializer)
                )
            }
            throw Exception()
        }
    }

    suspend fun <T, R> checkMessageWrapperAndConvertToModel(
        messageWrapperJson: String,
        serializer: KSerializer<T>,
        map: (T) -> R
    ): MessageWrapperModel<R> {
        return messageWrapperRepository.convertToModel(
            messageWrapper = checkMessageWrapperAndConvertToDTO(
                messageWrapperJson = messageWrapperJson,
                serializer = serializer,
                map = map
            ), map = map
        )
    }
}