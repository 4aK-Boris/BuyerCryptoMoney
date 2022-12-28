package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.BaseMapperTest
import java.time.LocalDate
import org.koin.core.component.inject

class DateMapperTest : BaseMapperTest<LocalDate, kotlinx.datetime.LocalDate>() {

    override val mapper by inject<DateMapper>()

    override suspend fun generateModel(): LocalDate {
        return LocalDate.now()
    }
}