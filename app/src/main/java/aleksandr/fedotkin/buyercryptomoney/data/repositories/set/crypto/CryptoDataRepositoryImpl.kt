package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto.CryptoDataMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.CryptoDataRepository

class CryptoDataRepositoryImpl(
    private val cryptoDataMapper: CryptoDataMapper
): CryptoDataRepository {

    override fun convertToModel(cryptoData: CryptoData): CryptoDataModel {
        return cryptoDataMapper.map(dto = cryptoData)
    }

    override fun convertToDTO(cryptoDataModel: CryptoDataModel): CryptoData {
        return cryptoDataMapper.map(model = cryptoDataModel)
    }
}
