package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CertificateRepository

class CertificateUseCase(
    private val certificateRepository: CertificateRepository,
    private val cardCInitReqUseCase: CardCInitReqUseCase
) {

    suspend fun getCertificate() {
        certificateRepository.getCertificate()
    }

    suspend fun sendCardCInitReq(): String {
        return cardCInitReqUseCase.sendCardCInitReq()
    }

    suspend fun cardCInit() {
        val messageWrapperJson = sendCardCInitReq()

    }
}