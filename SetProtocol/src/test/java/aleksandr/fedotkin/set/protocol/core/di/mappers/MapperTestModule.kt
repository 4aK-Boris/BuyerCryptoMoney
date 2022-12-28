package aleksandr.fedotkin.set.protocol.core.di.mappers

import aleksandr.fedotkin.set.protocol.core.di.mappers.certificate.certificateMappersTestModule
import aleksandr.fedotkin.set.protocol.core.di.mappers.core.coreMappersTestModule
import org.koin.dsl.module

val mapperTestModule = module {
    includes(
        coreMappersTestModule,
        generalMapperTestModule,
        errorMapperTestModule,
        certificateMappersTestModule,
        cryptoMapperTestModule
    )
}