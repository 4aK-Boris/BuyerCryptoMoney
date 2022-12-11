package aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core

import java.time.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime

class DateTimeMapper {

    fun map(dateTime: LocalDateTime): kotlinx.datetime.LocalDateTime {
        return dateTime.toKotlinLocalDateTime()
    }

    fun map(dateTime: kotlinx.datetime.LocalDateTime): LocalDateTime {
        return dateTime.toJavaLocalDateTime()
    }
}