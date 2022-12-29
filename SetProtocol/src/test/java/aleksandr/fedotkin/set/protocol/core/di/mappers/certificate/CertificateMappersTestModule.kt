package aleksandr.fedotkin.set.protocol.core.di.mappers.certificate

import org.koin.dsl.module

val certificateMappersTestModule = module {
    includes(
        cardCInitReqMapperTestModule,
        cardCInitResMapperTestModule,
        regFormReqMapperTestModule,
        regFormResMapperTestModule
    )
}