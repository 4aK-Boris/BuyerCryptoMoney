package aleksandr.fedotkin.set.protocol.data.repositories

import aleksandr.fedotkin.set.protocol.core.NUMBER_LENGTH
import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.data.dto.error.Error
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMapper
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorTBSMapper
import aleksandr.fedotkin.set.protocol.domain.models.Model
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorMsgModel
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.error.UnsignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageHeaderModel
import aleksandr.fedotkin.set.protocol.domain.repositories.ErrorRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.KeyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.SignatureRepository
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import kotlinx.serialization.KSerializer
import kotlin.random.Random

class ErrorRepositoryImpl(
    private val errorMapper: ErrorMapper,
    private val signatureRepository: SignatureRepository,
    private val keyRepository: KeyRepository,
    private val errorTBSMapper: ErrorTBSMapper
) : ErrorRepository {

    override suspend fun <T : DTO, R : Model> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ): Boolean? {
        val errorModel = convertToModel(error = error, map = map)
        return errorModel.unsignedErrorModel.errorTBSModel.errorThumb?.let { keyArray ->
            keyRepository.decodePublicKey(array = keyArray)
        }?.let { publicKey ->
            signatureRepository.verifySignature(
                data = error.unsignedError.errorTBS,
                signatureArray = errorModel.signedErrorModel.signature,
                serializer = serializer,
                publicKey = publicKey
            )
        }
    }

    override suspend fun <T : Model, R : DTO> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        certificate: X509Certificate,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R> {
        return convertToDTO(
            errorModel = createErrorModel(
                messageHeaderModel = messageHeaderModel,
                messageModel = messageModel,
                errorCode = errorCode,
                certificate = certificate,
                map = map,
                serializer = serializer,
                privateKey = privateKey
            ), map = map
        )
    }

    override suspend fun <T : Model, R : DTO> createError(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        publicKey: PublicKey,
        errorCode: ErrorCode,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): Error<R> {
        return convertToDTO(
            errorModel = createErrorModel(
                messageHeaderModel = messageHeaderModel,
                messageModel = messageModel,
                errorCode = errorCode,
                publicKey = publicKey,
                map = map,
                serializer = serializer,
                privateKey = privateKey
            ), map = map
        )
    }

    override fun <T : DTO, R : Model> convertToModel(
        error: Error<T>,
        map: (T) -> R
    ): ErrorModel<R> {
        return errorMapper.map(dto = error, map = map)
    }

    override fun <T : Model, R : DTO> convertToDTO(
        errorModel: ErrorModel<T>,
        map: (T) -> R
    ): Error<R> {
        return errorMapper.map(model = errorModel, map = map)
    }

    private suspend fun <T : Model, R : DTO> createErrorModel(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        errorCode: ErrorCode,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T> {
        return createErrorModel(
            messageHeaderModel = messageHeaderModel,
            messageModel = messageModel,
            errorCode = errorCode,
            publicKey = certificate.publicKey,
            map = map,
            serializer = serializer,
            privateKey = privateKey
        )
    }

    private suspend fun <T : Model, R : DTO> createErrorModel(
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T> {
        val unsignedErrorModel = createUnsignedErrorModel(
            errorCode = errorCode,
            publicKey = publicKey,
            messageHeaderModel = messageHeaderModel,
            messageModel = messageModel
        )
        val signedErrorModel = createSignedErrorModel(
            errorTBSModel = unsignedErrorModel.errorTBSModel,
            serializer = serializer,
            privateKey = privateKey,
            map = map
        )
        return ErrorModel(
            signedErrorModel = signedErrorModel,
            unsignedErrorModel = unsignedErrorModel
        )
    }

    private fun <T: Model> createUnsignedErrorModel(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        messageHeaderModel: MessageHeaderModel,
        messageModel: T,
    ): UnsignedErrorModel<T> {
        return UnsignedErrorModel(
            errorTBSModel = ErrorTBSModel(
                errorCode = errorCode,
                errorNonce = generateNewNumber(),
                errorOID = null,
                errorThumb = publicKey.encoded,
                errorMsgModel = ErrorMsgModel(
                    messageHeader = messageHeaderModel,
                    badWrapper = messageModel
                )
            )
        )
    }

    private suspend fun <T: Model, R: DTO> createSignedErrorModel(
        errorTBSModel: ErrorTBSModel<T>,
        serializer: KSerializer<R>,
        map: (T) -> R,
        privateKey: PrivateKey
    ): SignedErrorModel {
        val signature = signatureRepository.createSignature(
            data = errorTBSMapper.map(model = errorTBSModel, map = map),
            serializer = ErrorTBS.serializer(serializer),
            privateKey = privateKey
        )
        return SignedErrorModel(signature = signature)
    }

    private fun generateNewNumber(): BigInteger {
        return BigInteger(rnd.nextBytes(NUMBER_LENGTH))
    }

    companion object {
        private val rnd = Random
    }
}
