package aleksandr.fedotkin.buyercryptomoney.data.repositories.set

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorTBSMapper
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.ErrorMsgModel
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.SignedErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorTBSModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.UnsignedErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageHeaderModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.SignatureRepository
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

    override suspend fun <T, R> checkError(
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

    override suspend fun <T, R> createError(
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

    override suspend fun <T, R> createError(
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

    override suspend fun <T, R> createErrorModel(
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

    override suspend fun <T, R> createErrorModel(
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

    override fun <T, R> convertToModel(error: Error<T>, map: (T) -> R): ErrorModel<R> {
        return errorMapper.map(dto = error, map = map)
    }

    override fun <T, R> convertToDTO(errorModel: ErrorModel<T>, map: (T) -> R): Error<R> {
        return errorMapper.map(model = errorModel, map = map)
    }

    private fun <T> createUnsignedErrorModel(
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

    private suspend fun <T, R> createSignedErrorModel(
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
