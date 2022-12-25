package aleksandr.fedotkin.set.protocol.domain.useceses

import aleksandr.fedotkin.set.protocol.core.ChallengeMismatch
import aleksandr.fedotkin.set.protocol.core.SignatureFailure
import aleksandr.fedotkin.set.protocol.core.ThumbsMismatch
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormRes
import aleksandr.fedotkin.set.protocol.data.dto.error.ErrorCode
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.req.RegFormReqDataModel
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResModel
import aleksandr.fedotkin.set.protocol.domain.models.general.MessageWrapperModel
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.res.RegFormResRepository

class RegFormResUseCase(private val regFormResRepository: RegFormResRepository): ResponseUseCase<RegFormResModel, RegFormRes>() {

    override val serializer = RegFormRes.serializer()

    override val convertToDTO = regFormResRepository.convertToDTO

    override val convertToModel = regFormResRepository.convertToModel

    suspend fun checkRegFormRes(messageWrapperJson: String, regFormReqDataModel: RegFormReqDataModel, thumbs: List<ByteArray>): MessageWrapperModel<RegFormResModel> {
        return checkMessageWrapperAndConvertToModel(messageWrapperJson = messageWrapperJson).apply {
            checkSignature(messageWrapperModel = this)
            checkRequestType(messageWrapperModel = this, regFormReqDataModel = regFormReqDataModel)
            checkLIDEE(messageWrapperModel = this, regFormReqDataModel = regFormReqDataModel)
            checkChallEE2(messageWrapperModel = this, regFormReqDataModel = regFormReqDataModel)
            checkThumbs(messageWrapperModel = this, thumbs = thumbs)
        }
    }

    private suspend fun checkSignature(messageWrapperModel: MessageWrapperModel<RegFormResModel>) {
        if (!regFormResRepository.checkSignature(regFormResModel = messageWrapperModel.messageModel)) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.SignatureFailure,
            )
            throw SignatureFailure()
        }
    }

    private suspend fun checkRequestType(messageWrapperModel: MessageWrapperModel<RegFormResModel>, regFormReqDataModel: RegFormReqDataModel) {
        if (messageWrapperModel.messageModel.regFormResTBS.requestType != regFormReqDataModel.requestType) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
            )
            throw ChallengeMismatch()
        }
    }

    private suspend fun checkLIDEE(messageWrapperModel: MessageWrapperModel<RegFormResModel>, regFormReqDataModel: RegFormReqDataModel) {
        if (messageWrapperModel.messageModel.regFormResTBS.lidEE != regFormReqDataModel.lidEE) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
            )
            throw ChallengeMismatch()
        }
    }

    private suspend fun checkChallEE2(messageWrapperModel: MessageWrapperModel<RegFormResModel>, regFormReqDataModel: RegFormReqDataModel) {
        if (messageWrapperModel.messageModel.regFormResTBS.challEE2 != regFormReqDataModel.challEE2) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ChallengeMismatch,
            )
            throw ChallengeMismatch()
        }
    }

    private suspend fun checkThumbs(messageWrapperModel: MessageWrapperModel<RegFormResModel>, thumbs: List<ByteArray>) {
        if (messageWrapperModel.messageModel.regFormResTBS.thumbs != thumbs) {
            sendError(
                messageWrapperModel = messageWrapperModel,
                errorCode = ErrorCode.ThumbsMismatch,
            )
            throw ThumbsMismatch()
        }
    }
}