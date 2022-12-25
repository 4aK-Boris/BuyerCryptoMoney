package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import org.koin.dsl.module

val certificateMapperModule = module {

    includes(
        cardCInitReqMapperModule,
        cardCInitResMapperModule,
        regFormReqMapperModule,
        regFormResMapperModule,
        certReqMapperModule,
        certResMapperModule
    )
}
