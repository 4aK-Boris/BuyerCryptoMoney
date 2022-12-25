package aleksandr.fedotkin.set.protocol.domain.models.error

import aleksandr.fedotkin.set.protocol.domain.models.Model

class UnsignedErrorModel<T>(
    val errorTBSModel: ErrorTBSModel<T>
): Model