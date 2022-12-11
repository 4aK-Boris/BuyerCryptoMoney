package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.crypto.PANOnly
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.crypto.PANOnlyModel

interface PANOnlyRepository {

    fun convertToModel(panOnly: PANOnly): PANOnlyModel

    fun convertToDTO(panOnlyModel: PANOnlyModel): PANOnly

    suspend fun createPANOnlyModel(number: String): PANOnlyModel

    suspend fun convertToByteArray(panOnlyModel: PANOnlyModel): ByteArray
}
