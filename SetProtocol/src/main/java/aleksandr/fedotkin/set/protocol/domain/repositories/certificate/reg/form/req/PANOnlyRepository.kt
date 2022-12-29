package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.PANOnly
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel

interface PANOnlyRepository: BaseSetRepository<PANOnlyModel, PANOnly> {
    suspend fun create(number: String): PANOnlyModel
}
