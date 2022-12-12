package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres

import aleksandr.fedotkin.buyercryptomoney.data.dto.set.certificate.regformres.RegFormRes
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.regformres.RegFormResModel

class RegFormResMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val regFormResTBSMapper: RegFormResTBSMapper
) {

    fun map(model: RegFormResModel): RegFormRes {
        return RegFormRes(
            ca = byteArrayMapper.map(byteArray = model.ca),
            regFormResTBS = regFormResTBSMapper.map(model = model.regFormResTBS)
        )
    }

    fun map(dto: RegFormRes): RegFormResModel {
        return RegFormResModel(
            ca = byteArrayMapper.map(string = dto.ca),
            regFormResTBS = regFormResTBSMapper.map(model = dto.regFormResTBS)
        )
    }
}
