package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.SIGNATURE_ALGORITHM
import java.math.BigInteger
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Security
import java.security.cert.X509Certificate
import java.util.*
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder

class Certificate(val x509Certificate: X509Certificate) {

    private constructor(builder: Builder) : this(x509Certificate = builder.certificate)

    class Builder {

        private val bcProvider = BouncyCastleProvider()

        lateinit var certificateBuilder: JcaX509v3CertificateBuilder

        lateinit var certificate: X509Certificate

        private val startDate: Date
            get() = Date()

        private val endDate: Date
            get() {
                Calendar.getInstance().apply {
                    time = startDate
                    add(Calendar.YEAR, 1)
                    return this.time
                }
            }

        private val serialNumber: BigInteger
            get() {
                val now = System.currentTimeMillis()
                return BigInteger(now.toString())
            }

        init {
            Security.addProvider(bcProvider)
        }

        fun create(name: String, publicKey: PublicKey): Builder = apply {
            val x500Name = X500Name("CN=$name")
            certificateBuilder =
                JcaX509v3CertificateBuilder(x500Name, serialNumber, startDate, endDate, x500Name, publicKey)
        }

        fun create(name: String, publicKey: PublicKey, certificate: X509Certificate): Builder = apply {
            val x500Name = X500Name("CN=$name")
            certificateBuilder =
                JcaX509v3CertificateBuilder(certificate, serialNumber, startDate, endDate, x500Name, publicKey)
        }

        fun build(privateKey: PrivateKey): Certificate {
            val contentSigner = JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(privateKey)
            val x509CertificateHolder = certificateBuilder.build(contentSigner)
            this.certificate =
                JcaX509CertificateConverter().setProvider(bcProvider).getCertificate(x509CertificateHolder)
            return Certificate(builder = this)
        }
    }
}