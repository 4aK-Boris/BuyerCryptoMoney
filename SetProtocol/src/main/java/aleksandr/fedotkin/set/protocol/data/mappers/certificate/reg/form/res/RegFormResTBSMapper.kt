package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormResTBS
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import kotlinx.serialization.KSerializer

class RegFormResTBSMapper(
    private val bigIntegerMapper: BigIntegerMapper,
    private val base64Mapper: Base64Mapper,
    private val regFormOrReferralMapper: RegFormOrReferralMapper
) : SetMapper<RegFormResTBSModel, RegFormResTBS> {

    override val serializer: KSerializer<RegFormResTBS>
        get() = RegFormResTBS.serializer()

    override fun map(value: RegFormResTBSModel): RegFormResTBS {
        return RegFormResTBS(
            rrpID = bigIntegerMapper.map(value = value.rrpID),
            lidEE = bigIntegerMapper.map(value = value.lidEE),
            challEE2 = bigIntegerMapper.map(value = value.challEE2),
            lidCA = bigIntegerMapper.map(value = value.lidCA),
            challCA = bigIntegerMapper.map(value = value.challCA),
            caeThumb = base64Mapper.map(value = value.caeThumb),
            requestType = value.requestType,
            regFormOrReferral = regFormOrReferralMapper.map(value = value.regFormOrReferral),
            brandCRLIdentifier = value.brandCRLIdentifier.map { base64Mapper.map(value = it) },
            thumbs = value.thumbs.map { base64Mapper.map(value = it) }
        )
    }

    override fun reverseMap(value: RegFormResTBS): RegFormResTBSModel {
        return RegFormResTBSModel(
            rrpID = bigIntegerMapper.reverseMap(value = value.rrpID),
            lidEE = bigIntegerMapper.reverseMap(value = value.lidEE),
            challEE2 = bigIntegerMapper.reverseMap(value = value.challEE2),
            lidCA = bigIntegerMapper.reverseMap(value = value.lidCA),
            challCA = bigIntegerMapper.reverseMap(value = value.challCA),
            caeThumb = base64Mapper.reverseMap(value = value.caeThumb),
            requestType = value.requestType,
            regFormOrReferral = regFormOrReferralMapper.reverseMap(value = value.regFormOrReferral),
            brandCRLIdentifier = value.brandCRLIdentifier.map { base64Mapper.reverseMap(value = it) },
            thumbs = value.thumbs.map { base64Mapper.reverseMap(value = it) }
        )
    }
}
