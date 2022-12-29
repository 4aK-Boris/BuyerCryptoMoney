package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.dto.certificate.cert.req.CertReq
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncXMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.cert.req.CertReqModel

class CertReqMapper(
    private val encMapper: EncMapper,
    private val encXMapper: EncXMapper
) {

//    fun map(model: CertReqModel): CertReq {
//        return CertReq(
//            enc = encMapper.map(model = model.enc),
//            encX = encXMapper.map(model = model.encX)
//        )
//    }
//
//    fun map(dto: CertReq): CertReqModel {
//        return CertReqModel(
//            enc = encMapper.map(dto = dto.enc),
//            encX = encXMapper.map(dto = dto.encX)
//        )
//    }
}