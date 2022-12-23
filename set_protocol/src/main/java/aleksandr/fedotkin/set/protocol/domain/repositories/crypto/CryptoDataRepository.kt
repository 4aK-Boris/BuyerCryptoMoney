package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.CryptoData
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel

interface CryptoDataRepository {

    fun convertToModel(cryptoData: CryptoData): CryptoDataModel

    fun convertToDTO(cryptoDataModel: CryptoDataModel): CryptoData
}
