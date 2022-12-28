package aleksandr.fedotkin.set.protocol.core.mapper

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository
import javax.crypto.SecretKey
import org.koin.test.inject

abstract class BaseObjectMapperTest<T: Model, R: DTO>: BaseMapperTest<T, R>() {

    private val keyRepository by inject<KeyRepository>()

    protected fun generateSecretKey(): SecretKey {
        return keyRepository.generateSecretKey()
    }
}