package aleksandr.fedotkin.buyercryptomoney.domain.repositories.set.crypto

import java.security.KeyPair
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey

interface KeyRepository {

    suspend fun decodePublicKey(array: ByteArray): PublicKey

    suspend fun generateSecretKey(): SecretKey

    suspend fun generatePairKey(): KeyPair

    suspend fun createCertificate(certificate: ByteArray): X509Certificate
}