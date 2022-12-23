package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.data.di.mapper.certificate.certificateMapperModule
import org.koin.dsl.module

val mappersModule = module {
    includes(certificateMapperModule, coreMapperModule, errorMapperModule, generalMapperModule)
}
