package aleksandr.fedotkin.set.protocol.core.di.mappers.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapperTest
import org.koin.dsl.module

val cardCInitResMapperTestModule = module {

    factory {
        CardCInitResTBSMapperTest()
    }

    factory {
        CardCInitResMapperTest()
    }
}