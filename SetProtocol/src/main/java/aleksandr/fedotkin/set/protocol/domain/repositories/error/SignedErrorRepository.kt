package aleksandr.fedotkin.set.protocol.domain.repositories.error

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.error.SignedError
import aleksandr.fedotkin.set.protocol.domain.models.error.ErrorTBSModel
import aleksandr.fedotkin.set.protocol.domain.models.error.SignedErrorModel
import java.security.PrivateKey
import java.security.PublicKey

interface SignedErrorRepository<T: Model, R: DTO> : BaseSetRepository<SignedErrorModel, SignedError> {

    suspend fun createSignature(errorTBSModel: ErrorTBSModel<T>, privateKey: PrivateKey): SignedErrorModel

    suspend fun verify(errorTBSModel: ErrorTBSModel<T>, signature: SignedErrorModel, publicKey: PublicKey): Boolean
}