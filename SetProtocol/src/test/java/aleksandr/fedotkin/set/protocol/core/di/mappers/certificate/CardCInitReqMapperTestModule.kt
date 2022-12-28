package aleksandr.fedotkin.set.protocol.core.di.mappers.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapperTest
import org.koin.dsl.module

val cardCInitReqMapperTestModule = module {

    factory {
        CardCInitReqMapperTest()
    }
}