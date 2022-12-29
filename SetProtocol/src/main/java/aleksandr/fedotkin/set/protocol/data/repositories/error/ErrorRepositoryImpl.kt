package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.exception.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.ErrorTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.SignedErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.UnsignedErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.general.MessageWrapperRepository
import java.security.PrivateKey
import java.security.PublicKey

class ErrorRepositoryImpl<T : Model, R : DTO>(
    override val mapper: ErrorMapper<T, R>,
    private val unsignedErrorRepository: UnsignedErrorRepository<T, R>,
    private val signedErrorRepository: SignedErrorRepository<T, R>,
    private val errorTBSRepository: ErrorTBSRepository<T, R>,
    private val messageWrapperRepository: MessageWrapperRepository<ErrorModel<T>, Error<R>>
) : ErrorRepository<T, R> {

    override suspend fun verifyError(
        json: String,
    ): Boolean {
        return verifyErrorModel(errorModel = messageWrapperRepository.jsonToMessageWrapperModel(json = json).messageModel)
    }

    override suspend fun createErrorMessage(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        privateKey: PrivateKey,
        messageWrapperModel: MessageWrapperModel<T>
    ): String {
        return createErrorModel(
            errorCode = errorCode,
            publicKey = publicKey,
            privateKey = privateKey,
            messageWrapperModel = messageWrapperModel
        ).let { errorModel ->
            messageWrapperRepository.messageWrapperModelToJson(
                messageWrapperModel = createErrorMessageWrapper(
                    errorModel = errorModel
                )
            )
        }
    }

    private suspend fun verifyErrorModel(
        errorModel: ErrorModel<T>,
    ): Boolean {
        return with(errorModel.unsignedError) {
            val publicKey = errorTBSRepository.decodePublicKey(errorTBSModel = errorTBS)
            signedErrorRepository.verify(
                errorTBSModel = errorTBS, signature = errorModel.signedError, publicKey = publicKey
            )
        }
    }

    private suspend fun createErrorMessageWrapper(errorModel: ErrorModel<T>): MessageWrapperModel<ErrorModel<T>> {
        return messageWrapperRepository.create(
            rrpid = generateNewNumber(), message = errorModel
        )
    }

    private suspend fun createErrorModel(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        privateKey: PrivateKey,
        messageWrapperModel: MessageWrapperModel<T>
    ): ErrorModel<T> {
        val unsignedErrorModel = unsignedErrorRepository.create(
            publicKey = publicKey,
            errorCode = errorCode,
            messageHeader = messageWrapperModel.messageHeaderModel,
            message = messageWrapperModel.messageModel
        )
        val signedErrorModel = signedErrorRepository.createSignature(
            privateKey = privateKey, errorTBSModel = unsignedErrorModel.errorTBS
        )
        return ErrorModel(signedError = signedErrorModel, unsignedError = unsignedErrorModel)
    }

}
