package aleksandr.fedotkin.set.protocol.data.repositories.core

import aleksandr.fedotkin.set.protocol.core.CIPHER_ALGORITHM
import aleksandr.fedotkin.set.protocol.core.repository.RepositoryFunction
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
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
import javax.crypto.spec.SecretKeySpec

class KeyRepositoryImpl(
    private val keyFactory: KeyFactory,
    private val keyPairGenerator: KeyPairGenerator,
    private val keyGenerator: KeyGenerator,
    private val certificateFactory: CertificateFactory
): KeyRepository {

    @RepositoryFunction
    override fun decodePublicKey(array: ByteArray): PublicKey {
        return keyFactory.generatePublic(X509EncodedKeySpec(array))
    }

    @RepositoryFunction
    override fun decodeSecretKey(keyArray: ByteArray): SecretKey {
        return SecretKeySpec(keyArray, 0, KEY_LENGTH, CIPHER_ALGORITHM)
    }

    @RepositoryFunction
    override fun generateSecretKey(): SecretKey {
        return keyGenerator.generateKey()
    }

    @RepositoryFunction
    override fun generatePairKey(): KeyPair {
        return keyPairGenerator.generateKeyPair()
    }

    @RepositoryFunction
    override fun decodeCertificate(certificate: ByteArray): X509Certificate {
        return certificateFactory.generateCertificate(ByteArrayInputStream(certificate)) as X509Certificate
    }

    companion object {
        private const val KEY_LENGTH = 32
    }
}
