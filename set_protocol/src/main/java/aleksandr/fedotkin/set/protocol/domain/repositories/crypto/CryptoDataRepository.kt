package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.CryptoData
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel
import kotlinx.serialization.KSerializer

interface CryptoDataRepository {

    val serializer: KSerializer<CryptoData>

    val convertToModel: (CryptoData) -> CryptoDataModel

    val convertToDTO: (CryptoDataModel) -> CryptoData
}
