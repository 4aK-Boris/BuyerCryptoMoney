package aleksandr.fedotkin.set.protocol.data.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorTBS
import aleksandr.fedotkin.set.protocol.data.mappers.error.SignedErrorMapper
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel
import aleksandr.fedotkin.set.protocol.domain.repositories.core.SignatureRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.error.SignedErrorRepository
import java.security.PrivateKey
import java.security.PublicKey

class SignedErrorRepositoryImpl<T: Model, R: DTO>(
    override val mapper: SignedErrorMapper,
    private val signatureRepository: SignatureRepository<ErrorTBSModel<T>, ErrorTBS<R>>
): SignedErrorRepository<T, R> {

    override suspend fun createSignature(errorTBSModel: ErrorTBSModel<T>, privateKey: PrivateKey): SignedErrorModel {
        return SignedErrorModel(signature = signatureRepository.create(data = errorTBSModel, privateKey = privateKey))
    }

    override suspend fun verify(errorTBSModel: ErrorTBSModel<T>, signature: SignedErrorModel, publicKey: PublicKey): Boolean {
        return signatureRepository.verify(data = errorTBSModel, signatureArray = signature.signature, publicKey = publicKey)
    }
}