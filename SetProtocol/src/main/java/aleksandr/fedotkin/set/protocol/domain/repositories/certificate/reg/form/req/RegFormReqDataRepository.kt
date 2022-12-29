package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReqData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import java.math.BigInteger

interface RegFormReqDataRepository : BaseSetRepository<RegFormReqDataModel, RegFormReqData> {

    suspend fun create(
        rrpid: BigInteger,
        lidEE: BigInteger,
        lidCA: BigInteger
    ): RegFormReqDataModel

    suspend fun checkRRPID(rrpid: BigInteger, regFormReqDataModel: RegFormReqDataModel): Boolean
}