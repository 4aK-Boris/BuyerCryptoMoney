package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.PANData0
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.PANData0Model
import kotlinx.serialization.KSerializer

interface PanData0Repository {

    val serializer: KSerializer<PANData0>

    val convertToModel: (PANData0) -> PANData0Model

    val convertToDTO: (PANData0Model) -> PANData0

    suspend fun createPANData(month: String, year: String, number: String): PANData0Model
}