package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelMapper
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val coreMapperModule = module {

    factory { BigIntegerMapper() }

    factory { DateTimeMapper() }

    factory { DateMapper() }

    factory { ByteArrayMapper() }

    factory { Base64Mapper() }

    factory { (mapper: SetMapper<Model, DTO>) ->
        JsonMapper(mapper = mapper)
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        JsonByteArrayMapper<DTO>(jsonMapper = get { parametersOf(mapper) }, byteArrayMapper = get())
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        JsonModelMapper(jsonMapper = get { parametersOf(mapper) }, mapper = mapper)
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        JsonModelByteArrayMapper(
            jsonMapper = get { parametersOf(mapper) },
            mapper = mapper,
            byteArrayMapper = get()
        )
    }
}