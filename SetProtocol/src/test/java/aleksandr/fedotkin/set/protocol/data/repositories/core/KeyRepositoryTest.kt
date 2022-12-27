package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.RSA
import aleksandr.fedotkin.set.protocol.core.SYMMETRIC_KEY_LENGTH
import aleksandr.fedotkin.set.protocol.core.di.certificate.Certificate
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.core.repository.After
import aleksandr.fedotkin.set.protocol.core.repository.BaseTestRepository
import aleksandr.fedotkin.set.protocol.core.repository.DefaultValue
import aleksandr.fedotkin.set.protocol.core.repository.TestFunction
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.test.get
import org.koin.test.inject
import kotlin.reflect.KClass
import kotlin.test.assertEquals

@Suppress("unused")
@After
class KeyRepositoryTest: BaseTestRepository() {

    override val repository by inject<KeyRepository>()

    override val clazz: KClass<*>
        get() = this::class

    @DefaultValue(name = "decodePublicKey")
    fun generatePublicKey(): PublicKey {
        return repository.generatePairKey().public
    }

    @TestFunction(name = "decodePublicKey")
    fun testDecodePublicKey(publicKey: PublicKey): ByteArray {
        return publicKey.encoded
    }

    @DefaultValue("decodeSecretKey")
    fun generateSecretKey(): SecretKey {
        return repository.generateSecretKey()
    }

    @TestFunction(name = "decodeSecretKey")
    fun testDecodeSecretKey(secretKey: SecretKey): ByteArray {
        return secretKey.encoded
    }

    @DefaultValue("decodeCertificate")
    fun generateCertificate(): X509Certificate {
        return get<Certificate>(qualifier = named(CertificateQualifiers.RCA)).x509Certificate
    }

    @TestFunction(name = "decodeCertificate")
    fun testDecodeCertificate(certificate: X509Certificate): ByteArray {
        return certificate.encoded
    }

    @Test
    fun testSecretKeyLength() {
        val length = repository.generateSecretKey().encoded.size * 8
        assertEquals(expected = length, actual = SYMMETRIC_KEY_LENGTH)
    }

    @Test
    fun testKeyPairLength() {
        val (publicKey, privateKey) = repository.generatePairKey().let { it.public to it.private }
        assertEquals(expected = publicKey.algorithm, actual = RSA)
        assertEquals(expected = privateKey.algorithm, actual = RSA)
    }
}