package aleksandr.fedotkin.set.protocol.core.repository

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper

interface BaseSetRepository<T: Model, R: DTO>: BaseRepository {

    val mapper: SetMapper<T, R>
}