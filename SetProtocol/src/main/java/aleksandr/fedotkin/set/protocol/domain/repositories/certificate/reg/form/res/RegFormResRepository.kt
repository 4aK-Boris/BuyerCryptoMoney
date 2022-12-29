package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import kotlinx.serialization.KSerializer

interface RegFormResRepository {

    val serializer: KSerializer<RegFormRes>

    val convertToModel: (RegFormRes) -> RegFormResModel

    val convertToDTO: (RegFormResModel) -> RegFormRes

    suspend fun checkSignature(regFormResModel: RegFormResModel): Boolean
}