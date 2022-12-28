package aleksandr.fedotkin.set.protocol.data.mappers.core

import aleksandr.fedotkin.set.protocol.core.mapper.CoreMapper
import java.time.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate

class DateMapper: CoreMapper<LocalDate, kotlinx.datetime.LocalDate> {

    override fun map(value: LocalDate): kotlinx.datetime.LocalDate {
        return value.toKotlinLocalDate()
    }

    override fun reverseMap(value: kotlinx.datetime.LocalDate): LocalDate {
        return value.toJavaLocalDate()
    }
}