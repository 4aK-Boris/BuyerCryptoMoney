package aleksandr.fedotkin.buyercryptomoney.domain.usecases.set

import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate.CertificateRepository

class CertificateUseCase(
    private val certificateRepository: CertificateRepository
) {

    suspend fun getCertificate() {
        certificateRepository.getCertificate()
    }
}