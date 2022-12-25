package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageHeaderMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageIDMapper
import aleksandr.fedotkin.set.protocol.data.mappers.general.MessageWrapperMapper
import org.koin.dsl.module

val generalMapperModule = module {

    factory {
        MessageIDMapper(
            bigIntegerMapper = get()
        )
    }

    factory {
        MessageWrapperMapper(
            messageHeaderMapper = get()
        )
    }

    factory {
        MessageHeaderMapper(
            messageIDMapper = get(),
            bigIntegerMapper = get(),
            dateTimeMapper = get()
        )
    }
}