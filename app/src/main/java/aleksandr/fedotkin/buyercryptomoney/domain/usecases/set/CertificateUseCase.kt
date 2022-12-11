package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitReqModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.certificate.CardCInitResModel
import aleksandr.fedotkin.buyercryptomoney.domain.model.set.general.MessageWrapperModel
import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CertificateRepository

class CertificateUseCase(
    private val certificateRepository: CertificateRepository,
    private val cardCInitReqUseCase: CardCInitReqUseCase,
    private val cardCInitResUseCase: CardCInitResUseCase
) {

    suspend fun getCertificate() {
        certificateRepository.getCertificate("")
    }

    private suspend fun sendCardCInitReq(): Pair<String, CardCInitReqModel> {
        return cardCInitReqUseCase.sendCardCInitReq()
    }

    suspend fun cardCInit(): MessageWrapperModel<CardCInitResModel> {
        return sendCardCInitReq().run {
            cardCInitResUseCase.checkCardCInitRes(
                messageWrapperJson = first,
                challEE = second.challEE,
                rrpid = second.rrpID,
                thumbs = second.thumbs
            )
        }
    }
}
