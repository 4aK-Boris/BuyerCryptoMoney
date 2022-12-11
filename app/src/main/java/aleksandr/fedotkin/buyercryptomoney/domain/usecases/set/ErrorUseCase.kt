package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.domain.common.BaseUseCase
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.domain.common.SetError
import aleksandr.fedotkin.buyercryptomoney.domain.common.SignatureError
import kotlinx.serialization.KSerializer

class ErrorUseCase(
    private val errorRepository: ErrorRepository
) : BaseUseCase() {

    suspend fun <T, R> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ) {
        val verify = errorRepository.checkError(error = error, map = map, serializer = serializer)
        if (verify != null && verify == false) {
            throw SignatureError()
        } else {
            throw SetError()
        }
    }
}
