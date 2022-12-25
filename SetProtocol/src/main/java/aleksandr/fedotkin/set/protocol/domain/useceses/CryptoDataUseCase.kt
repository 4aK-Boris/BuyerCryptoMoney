package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.crypto.CryptoData
import aleksandr.fedotkin.set.protocol.domain.models.crypto.CryptoDataModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.CryptoDataRepository

class CryptoDataUseCase(
    cryptoDataRepository: CryptoDataRepository
): MessageWrapperUseCase<CryptoDataModel, CryptoData>() {

    override val serializer = cryptoDataRepository.serializer

    override val convertToModel = cryptoDataRepository.convertToModel

    override val convertToDTO = cryptoDataRepository.convertToDTO

    suspend fun cryptoDataModelToJson(messageWrapperModel: MessageWrapperModel<CryptoDataModel>): String {
        return messageWrapperToJson(messageWrapper = convertToDTO(messageWrapperModel = messageWrapperModel))
    }
}