package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel

interface CryptoDataRepository {

    fun convertToModel(cryptoData: CryptoData): CryptoDataModel

    fun convertToDTO(cryptoDataModel: CryptoDataModel): CryptoData
}
