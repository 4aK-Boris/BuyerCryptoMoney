package aleksandr.fedotkin.buyercryptomoney.data.dto.set.error

import kotlinx.serialization.Serializable

@Serializable
class UnsignedError<T>(
    val errorTBS: ErrorTBS<T>
)