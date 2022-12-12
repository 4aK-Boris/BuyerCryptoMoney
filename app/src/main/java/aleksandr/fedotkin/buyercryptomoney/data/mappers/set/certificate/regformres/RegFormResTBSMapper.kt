package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.RegFormResTBS
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.RegFormResTBSModel

class RegFormResTBSMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val byteArrayMapper: ByteArrayMapper,
    private val regFormOrReferralMapper: RegFormOrReferralMapper
) {

    fun map(model: RegFormResTBSModel): RegFormResTBS {
        return RegFormResTBS(
            rrpID = bigIntegerMapper.map(number = model.rrpID),
            lidEE = bigIntegerMapper.map(number = model.lidEE),
            challEE2 = bigIntegerMapper.map(number = model.challEE2),
            lidCA = bigIntegerMapper.map(number = model.lidCA),
            challCA = bigIntegerMapper.map(number = model.challCA),
            caeThumb = byteArrayMapper.map(byteArray = model.caeThumb),
            requestType = model.requestType,
            regFormOrReferral = regFormOrReferralMapper.map(model = model.regFormOrReferral),
            brandCRLIdentifier = model.brandCRLIdentifier.map { byteArrayMapper.map(byteArray = it) },
            thumbs = model.thumbs.map { byteArrayMapper.map(byteArray = it) }
        )
    }

    fun map(model: RegFormResTBS): RegFormResTBSModel {
        return RegFormResTBSModel(
            rrpID = bigIntegerMapper.map(string = model.rrpID),
            lidEE = bigIntegerMapper.map(string = model.lidEE),
            challEE2 = bigIntegerMapper.map(string = model.challEE2),
            lidCA = bigIntegerMapper.map(string = model.lidCA),
            challCA = bigIntegerMapper.map(string = model.challCA),
            caeThumb = byteArrayMapper.map(string = model.caeThumb),
            requestType = model.requestType,
            regFormOrReferral = regFormOrReferralMapper.map(dto = model.regFormOrReferral),
            brandCRLIdentifier = model.brandCRLIdentifier.map { byteArrayMapper.map(string = it) },
            thumbs = model.thumbs.map { byteArrayMapper.map(string = it) }
        )
    }
}
