package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapper
import aleksandr.fedotkin.set.protocol.data.mappers.core.JsonMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreMapperModule = module {

    factoryOf(::JsonMapper)

    factoryOf(::BigIntegerMapper)

    factoryOf(::DateTimeMapper)

    factoryOf(::ByteArrayMapper)
}