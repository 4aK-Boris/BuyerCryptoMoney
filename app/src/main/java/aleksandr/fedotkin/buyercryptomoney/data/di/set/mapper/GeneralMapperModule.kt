package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageHeaderMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageIDMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.general.MessageWrapperMapper
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