package aleksandr.fedotkin.set.protocol.core.di.mappers.core

import aleksandr.fedotkin.set.protocol.data.mappers.core.Base64MapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.BigIntegerMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.DateTimeMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelByteArrayMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.core.json.JsonModelMapperTest
import org.koin.dsl.module

val coreMappersTestModule = module {

    factory { JsonMapperTest() }

    factory { JsonByteArrayMapperTest() }

    factory { JsonModelMapperTest() }

    factory { JsonModelByteArrayMapperTest() }

    factory { BigIntegerMapperTest() }

    factory { ByteArrayMapperTest() }

    factory { DateMapperTest() }

    factory { DateTimeMapperTest() }

    factory { Base64MapperTest() }
}