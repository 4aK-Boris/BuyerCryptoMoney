package aleksandr.fedotkin.buyercryptomoney.data.repositories.set.crypto

import aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto.KeyRepository
import java.io.ByteArrayInputStream
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PublicKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class KeyRepositoryImpl(
    private val keyFactory: KeyFactory,
    private val keyPairGenerator: KeyPairGenerator,
    private val keyGenerator: KeyGenerator,
    private val certificateFactory: CertificateFactory
): KeyRepository {

    override suspend fun decodePublicKey(array: ByteArray): PublicKey {
        val publicKeySpec = X509EncodedKeySpec(array)
        return keyFactory.generatePublic(publicKeySpec)
    }

    override suspend fun generateSecretKey(): SecretKey {
        return keyGenerator.generateKey()
    }

    override suspend fun generatePairKey(): KeyPair {
        return keyPairGenerator.generateKeyPair()
    }

    override suspend fun createCertificate(certificate: ByteArray): X509Certificate {
        val inputStream = ByteArrayInputStream(certificate)
        return certificateFactory.generateCertificate(inputStream) as X509Certificate
    }
}