package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.data.dto.Language
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqDataRepository
import java.math.BigInteger

class RegFormReqDataRepositoryImpl(
    override val mapper: RegFormReqDataMapper
) : RegFormReqDataRepository {

    override suspend fun create(
        rrpid: BigInteger,
        lidEE: BigInteger,
        lidCA: BigInteger
    ): RegFormReqDataModel {
        return RegFormReqDataModel(
            rrpID = rrpid,
            lidEE = lidEE,
            challEE2 = generateNewNumber(),
            lidCA = lidCA,
            requestType = RequestType.SIGNATURE,
            language = Language.RUSSIAN,
            thumbs = emptyList()
        )
    }

    override suspend fun checkRRPID(rrpid: BigInteger, regFormReqDataModel: RegFormReqDataModel): Boolean {
        return rrpid == regFormReqDataModel.rrpID
    }
}