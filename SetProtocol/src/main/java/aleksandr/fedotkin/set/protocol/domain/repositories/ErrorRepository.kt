package aleksandr.fedotkin.set.protocol.domain.repositories

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer

interface ErrorRepository {

    suspend fun <T: DTO, R: Model> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ): Boolean?

    suspend fun <T: Model, R: DTO> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R>

    suspend fun <T: Model, R: DTO> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R>

    fun <T: DTO, R: Model> convertToModel(error: Error<T>, map: (T) -> R): ErrorModel<R>

    fun <T: Model, R: DTO> convertToDTO(errorModel: ErrorModel<T>, map: (T) -> R): Error<R>
}
