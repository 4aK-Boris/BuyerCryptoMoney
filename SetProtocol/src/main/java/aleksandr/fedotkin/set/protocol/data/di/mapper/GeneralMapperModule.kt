package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
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

    factory { (mapper: SetMapper<Model, DTO>) ->
        MessageWrapperMapper(
            messageHeaderMapper = get(),
            mapper = mapper
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