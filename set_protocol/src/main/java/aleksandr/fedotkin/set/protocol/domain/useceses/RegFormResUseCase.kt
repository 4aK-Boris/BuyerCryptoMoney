package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository

class RegFormResUseCase(private val regFormResRepository: RegFormResRepository): ResponseUseCase<RegFormResModel, RegFormRes>() {

    override val serializer = RegFormRes.serializer()

    override val convertToDTO = regFormResRepository.convertToDTO

    override val convertToModel = regFormResRepository.convertToModel

    suspend fun checkRegFormRes(messageWrapperJson: String): MessageWrapperModel<RegFormResModel> {
        return checkRegFormRes(messageWrapperJson = messageWrapperJson)
    }
}