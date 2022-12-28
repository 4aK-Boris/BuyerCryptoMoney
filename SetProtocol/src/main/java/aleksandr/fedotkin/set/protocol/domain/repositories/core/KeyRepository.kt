package aleksandr.fedotkin.set.protocol.domain.repositories.core

import aleksandr.fedotkin.set.protocol.core.repository.BaseRepository
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.X509Certificate
import javax.crypto.SecretKey

interface KeyRepository: BaseRepository {

    fun decodePublicKey(array: ByteArray): PublicKey

    fun decodeSecretKey(keyArray: ByteArray): SecretKey

    fun generateSecretKey(): SecretKey

    fun generatePairKey(): Pair<PublicKey, PrivateKey>

    fun decodeCertificate(certificate: ByteArray): X509Certificate
}
