package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.data.mappers.error.UnsignedErrorMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.UnsignedErrorRepository
import java.security.PublicKey

class UnsignedErrorRepositoryImpl<T : Model, R : DTO>(
    override val mapper: UnsignedErrorMapper<T, R>,
    private val errorTBSRepository: ErrorTBSRepository<T, R>
) : UnsignedErrorRepository<T, R> {

    override suspend fun create(
        publicKey: PublicKey,
        errorCode: ErrorCode,
        messageHeader: MessageHeaderModel,
        message: T
    ): UnsignedErrorModel<T> {
        return UnsignedErrorModel(
            errorTBS = errorTBSRepository.create(
                publicKey = publicKey,
                errorCode = errorCode,
                messageHeader = messageHeader,
                message = message
            )
        )
    }
}