package aleksandr.fedotkin.set.protocol.core.di.repositories

import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryTest
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResTBSRepositoryTest
import org.koin.dsl.module

val certificateRepositoryTestModule = module {

    factory {
        CardCInitReqRepositoryTest()
    }

    factory {
        CardCInitResRepositoryTest()
    }

    factory {
        CardCInitResTBSRepositoryTest()
    }
}