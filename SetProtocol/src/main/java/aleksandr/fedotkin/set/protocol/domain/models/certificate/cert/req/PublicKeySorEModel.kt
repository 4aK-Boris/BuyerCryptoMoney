package aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req

import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.security.PublicKey

data class PublicKeySorEModel(
    val publicKeyS: PublicKey?,
    val publicKeyE: PublicKey?
) : Model
