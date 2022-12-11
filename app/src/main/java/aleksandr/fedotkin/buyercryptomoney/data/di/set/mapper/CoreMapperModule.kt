package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.ByteArrayMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.JsonMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.BigIntegerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.core.DateTimeMapper

val coreMapperModule = module {

    factoryOf(::JsonMapper)

    factoryOf(::BigIntegerMapper)

    factoryOf(::DateTimeMapper)

    factoryOf(::ByteArrayMapper)
}