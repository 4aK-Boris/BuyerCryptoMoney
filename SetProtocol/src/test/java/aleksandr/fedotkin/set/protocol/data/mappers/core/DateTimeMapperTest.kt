package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapperTest
import java.time.LocalDateTime
import org.koin.core.component.inject

class DateTimeMapperTest : BaseMapperTest<LocalDateTime, kotlinx.datetime.LocalDateTime>() {

    override val mapper by inject<DateTimeMapper>()

    override suspend fun generateModel(): LocalDateTime {
        return LocalDateTime.now()
    }
}