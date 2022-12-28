package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req

import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.req.PANOnly
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.PANOnlyModel
import kotlinx.serialization.KSerializer

class PANOnlyMapper(
    private val bigIntegerMapper: BigIntegerMapper
): SetMapper<PANOnlyModel, PANOnly> {

    override val serializer: KSerializer<PANOnly>
        get() = PANOnly.serializer()

    override fun map(value: PANOnlyModel): PANOnly {
        return PANOnly(
            pan = bigIntegerMapper.map(value = value.pan),
            exNonce = bigIntegerMapper.map(value = value.exNonce)
        )
    }

    override fun reverseMap(value: PANOnly): PANOnlyModel {
        return PANOnlyModel(
            pan = bigIntegerMapper.reverseMap(value = value.pan),
            exNonce = bigIntegerMapper.reverseMap(value = value.exNonce)
        )
    }
}