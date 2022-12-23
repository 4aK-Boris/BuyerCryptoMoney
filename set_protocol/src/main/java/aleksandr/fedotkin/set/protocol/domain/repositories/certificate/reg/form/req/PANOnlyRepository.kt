package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.dto.crypto.PANOnly
import aleksandr.fedotkin.set.protocol.domain.models.crypto.PANOnlyModel
import kotlinx.serialization.KSerializer

interface PANOnlyRepository {

    val convertToModel: (PANOnly) -> PANOnlyModel

    val convertToDTO: (PANOnlyModel) -> PANOnly

    val serializer: KSerializer<PANOnly>

    suspend fun createPANOnlyModel(number: String): PANOnlyModel
}
