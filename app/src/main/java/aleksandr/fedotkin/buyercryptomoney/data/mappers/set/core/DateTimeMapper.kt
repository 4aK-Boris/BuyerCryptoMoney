package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core

import java.time.LocalDate
import java.time.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalDateTime

class DateTimeMapper {

    fun map(dateTime: LocalDateTime): kotlinx.datetime.LocalDateTime {
        return dateTime.toKotlinLocalDateTime()
    }

    fun map(dateTime: kotlinx.datetime.LocalDateTime): LocalDateTime {
        return dateTime.toJavaLocalDateTime()
    }

    fun map(date: LocalDate): kotlinx.datetime.LocalDate {
        return date.toKotlinLocalDate()
    }

    fun map(date: kotlinx.datetime.LocalDate): LocalDate {
        return date.toJavaLocalDate()
    }
}
