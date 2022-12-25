package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.AcctData
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.AcctDataModel

class AcctDataMapper(
    private val bigIntegerMapper: BigIntegerMapper
) {

    fun map(model: AcctDataModel): AcctData {
        return AcctData(
            acctIdentification = bigIntegerMapper.map(number = model.acctIdentification),
            exNonce = bigIntegerMapper.map(number = model.exNonce)
        )
    }

    fun map(dto: AcctData): AcctDataModel {
        return AcctDataModel(
            acctIdentification = bigIntegerMapper.map(string = dto.acctIdentification),
            exNonce = bigIntegerMapper.map(string = dto.exNonce)
        )
    }
}
