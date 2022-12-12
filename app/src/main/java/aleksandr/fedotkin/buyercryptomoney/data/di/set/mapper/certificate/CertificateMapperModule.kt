package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

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
