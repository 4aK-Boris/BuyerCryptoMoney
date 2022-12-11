package aleksandr.fedotkin.buyercryptomoney.data.repositories.set

import aleksandr.fedotkin.buyercryptomoney.core.NUMBER_LENGTH
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.Error
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorCode
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.error.ErrorTBS
import aleksandr.fedotkin.buyercryptomoney.data.dto.set.general.MessageWrapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorTBSMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageWrapperMapper
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.ErrorMsgModel
import aleksandr.fedotkin.buyercryptomoney.data.repositories.set.error.error.SignedErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.common.SetError
import aleksandr.fedotkin.buyercryptomoney.domain.common.SignatureError
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.ErrorTBSModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.error.UnsignedErrorModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.ErrorRepository
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.MessageWrapperRepository
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
    private val errorTBSMapper: ErrorTBSMapper,
    private val messageWrapperRepository: MessageWrapperRepository,
    private val messageWrapperMapper: MessageWrapperMapper
) : ErrorRepository {

    override suspend fun <T, R> checkError(
        error: Error<T>,
        map: (T) -> R,
        serializer: KSerializer<ErrorTBS<T>>
    ): ErrorModel<R> {
        val errorModel = errorMapper.map(error = error, map = map)
        val publicKey = errorModel.unsignedErrorModel.errorTBSModel.errorThumb?.let {
            keyRepository.decodePublicKey(array = it)
        }
        val verify = publicKey?.let {
            signatureRepository.verifySignature(
                data = error.unsignedError.errorTBS,
                signatureArray = errorModel.signedErrorModel.signature,
                serializer = serializer,
                publicKey = it
            )
        }
        if (verify != null && verify == false) throw SignatureError()
        else throw SetError()
    }

    override suspend fun <T, R> createErrorModel(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        certificate: X509Certificate,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T> {
        return createErrorModel(
            messageWrapperModel,
            errorCode,
            certificate.publicKey,
            map,
            serializer,
            privateKey
        )
    }

    override suspend fun <T, R> createErrorModel(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): ErrorModel<T> {
        val unsignedErrorModel = createUnsignedErrorModel(
            errorCode = errorCode,
            publicKey = publicKey,
            messageWrapperModel = messageWrapperModel
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

    override suspend fun <T, R> createErrorMessageWrapper(
        messageWrapperModel: MessageWrapperModel<T>,
        errorCode: ErrorCode,
        publicKey: PublicKey,
        map: (T) -> R,
        serializer: KSerializer<R>,
        privateKey: PrivateKey
    ): MessageWrapper<Error<R>> {
        val errorModel = createErrorModel(
            messageWrapperModel = messageWrapperModel,
            errorCode = errorCode,
            publicKey = publicKey,
            privateKey = privateKey,
            serializer = serializer,
            map = map
        )
        val errorMessageWrapperModel = messageWrapperRepository.changeMessage(
            messageWrapperModel = messageWrapperModel,
            messageModel = errorModel
        )
        return messageWrapperMapper.map(messageWrapperModel = errorMessageWrapperModel, errorMap = errorMapper::map, map = map)
    }

    private fun <T> createUnsignedErrorModel(
        errorCode: ErrorCode,
        publicKey: PublicKey,
        messageWrapperModel: MessageWrapperModel<T>
    ): UnsignedErrorModel<T> {
        return UnsignedErrorModel(
            errorTBSModel = ErrorTBSModel(
                errorCode = errorCode,
                errorNonce = generateNewNumber(),
                errorOID = null,
                errorThumb = publicKey.encoded,
                errorMsgModel = ErrorMsgModel(
                    messageHeader = messageWrapperModel.messageHeaderModel,
                    badWrapper = messageWrapperModel.messageModel
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
            data = errorTBSMapper.map(errorTBSModel = errorTBSModel, map = map),
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