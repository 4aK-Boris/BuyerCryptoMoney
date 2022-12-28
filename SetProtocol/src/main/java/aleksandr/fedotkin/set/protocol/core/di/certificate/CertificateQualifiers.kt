package aleksandr.fedotkin.set.protocol.core.di.certificate

enum class CertificateQualifiers(val keySize: Int) {
    RCA(keySize = 2048),
    BCA(keySize = 2048),
    CCA(keySize = 2048),
    GCA(keySize = 2048),
    MCA(keySize = 2048),
    PCA(keySize = 2048);

    val title = "${this.name} Александр Федоткин"
}