package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.crypto

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.CryptoData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.CryptoDataModel

class CryptoDataMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(model: CryptoDataModel): CryptoData {
        return CryptoData(
            cipherData = byteArrayMapper.map(byteArray = model.cipherData),
            oaep = byteArrayMapper.map(byteArray = model.oaep)
        )
    }
}