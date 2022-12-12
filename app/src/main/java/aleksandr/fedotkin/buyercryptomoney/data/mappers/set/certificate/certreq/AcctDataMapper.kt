package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.certreq.AcctData
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.certreq.AcctDataModel

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
