package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.certificate

interface CertificateRepository {

    suspend fun getCertificate(number: String)
}