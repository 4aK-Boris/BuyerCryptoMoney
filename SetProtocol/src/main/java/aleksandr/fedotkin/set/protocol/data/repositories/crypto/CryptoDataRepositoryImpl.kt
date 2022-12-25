package aleksandr.fedotkin.set.protocol.data.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.CryptoData
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.CryptoDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CryptoDataRepository

class CryptoDataRepositoryImpl(
    private val cryptoDataMapper: CryptoDataMapper
): CryptoDataRepository {

    override val serializer = CryptoData.serializer()

    override val convertToModel: (CryptoData) -> CryptoDataModel = cryptoDataMapper::map

    override val convertToDTO: (CryptoDataModel) -> CryptoData = cryptoDataMapper::map
}
