package aleksandr.fedotkin.set.protocol.domain.models.crypto

import aleksandr.fedotkin.set.protocol.domain.models.Model
import javax.crypto.SecretKey

data class OAEP2Model<T: Model> (
    val secretKey: SecretKey,
    val p: T
): Model