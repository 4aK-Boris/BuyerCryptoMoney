package aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.repository.BaseSetRepository
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.RegFormReq
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqModel
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey

interface RegFormReqRepository : BaseSetRepository<RegFormReqModel, RegFormReq> {

    suspend fun create(
        rrpid: BigInteger,
        lidEE: BigInteger,
        lidCA: BigInteger,
        number: String,
        publicKey: PublicKey
    ): RegFormReqModel

    suspend fun decryptAndCheck(
        rrpid: BigInteger,
        privateKey: PrivateKey,
        model: RegFormReqModel
    ): Pair<RegFormReqDataModel, PANOnlyModel>
}
