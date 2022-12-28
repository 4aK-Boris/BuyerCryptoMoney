package aleksandr.fedotkin.set.protocol.domain.models.crypto.oaep

import aleksandr.fedotkin.set.protocol.core.Model
import javax.crypto.SecretKey

data class OAEP3Model<T: Model> (val secretKey: SecretKey, val data: T): Model