package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import java.security.KeyPair
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey

interface KeyRepository {

    fun decodePublicKey(array: ByteArray): PublicKey

    fun decodeSecretKey(keyArray: ByteArray): SecretKey

    suspend fun generateSecretKey(): SecretKey

    suspend fun generatePairKey(): KeyPair

    suspend fun decodeCertificate(certificate: ByteArray): X509Certificate
}
