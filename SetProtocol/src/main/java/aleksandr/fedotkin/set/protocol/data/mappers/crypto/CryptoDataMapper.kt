package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.CryptoData
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel

class CryptoDataMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

    fun map(model: CryptoDataModel): CryptoData {
        return CryptoData(
            cipherData = byteArrayMapper.map(byteArray = model.cipherData),
            oaep = byteArrayMapper.map(byteArray = model.oaep)
        )
    }

    fun map(dto: CryptoData): CryptoDataModel {
        return CryptoDataModel(
            cipherData = byteArrayMapper.map(string = dto.cipherData),
            oaep = byteArrayMapper.map(string = dto.oaep)
        )
    }
}
