package aleksandr.fedotkin.set.protocol.core.di.certificate

import aleksandr.fedotkin.set.protocol.core.RSA
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.cert.X509Certificate

class CryptoBuilder {

    private val secureRandom = SecureRandom()

    fun generatePairKey(keySize: Int): KeyPair {
        return KeyPairGenerator.getInstance(RSA).apply {
            initialize(keySize, secureRandom)
        }.generateKeyPair()
    }

    fun createRootCertificate(
        keyPair: KeyPair,
        name: String,
        builder: Certificate.Builder
    ): Certificate {
        return builder
            .create(name = name, publicKey = keyPair.public)
            .build(privateKey = keyPair.private)
    }

    fun createCertificate(
        keyPair: KeyPair,
        name: String,
        certificate: X509Certificate,
        builder: Certificate.Builder
    ): Certificate {
        return builder
            .create(name = name, publicKey = keyPair.public, certificate = certificate)
            .build(privateKey = keyPair.private)
    }
}