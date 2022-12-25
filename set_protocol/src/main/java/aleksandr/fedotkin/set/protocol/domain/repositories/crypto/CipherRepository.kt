package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

import aleksandr.fedotkin.set.protocol.data.dto.DTO
import aleksandr.fedotkin.set.protocol.domain.models.Model
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey
import kotlinx.serialization.KSerializer

interface CipherRepository {

    suspend fun <T: Model, R: DTO> asymmetricEncrypt(
        model: T,
        serializer: KSerializer<R>,
        publicKey: PublicKey,
        map: (T) -> R
    ): ByteArray

    suspend fun <T: Model, R: DTO> asymmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<R>,
        privateKey: PrivateKey,
        map: (R) -> T
    ): T

    suspend fun <R: DTO> asymmetricEncrypt(
        data: R,
        serializer: KSerializer<R>,
        publicKey: PublicKey
    ): ByteArray

    suspend fun <R: DTO> asymmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<R>,
        privateKey: PrivateKey
    ): R

    suspend fun asymmetricEncrypt(
        data: ByteArray,
        publicKey: PublicKey
    ): ByteArray

    suspend fun asymmetricDecrypt(
        data: ByteArray,
        privateKey: PrivateKey
    ): ByteArray

    suspend fun <T : Model, R : DTO> symmetricEncrypt(
        model: T,
        map: (T) -> R,
        serializer: KSerializer<R>,
        secretKey: SecretKey
    ): ByteArray

    suspend fun <T : Model, R : DTO> symmetricDecrypt(
        data: ByteArray,
        map: (R) -> T,
        deserializer: KSerializer<R>,
        secretKey: SecretKey
    ): T

    suspend fun <T : DTO> symmetricEncrypt(
        data: T,
        serializer: KSerializer<T>,
        secretKey: SecretKey
    ): ByteArray

    suspend fun <T : DTO> symmetricDecrypt(
        data: ByteArray,
        deserializer: KSerializer<T>,
        secretKey: SecretKey
    ): T

    suspend fun symmetricEncrypt(data: ByteArray, secretKey: SecretKey): ByteArray

    suspend fun symmetricDecrypt(data: ByteArray, secretKey: SecretKey): ByteArray
}