package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import org.koin.dsl.module

val cardCInitResMapperModule = module {

    factory {
        CardCInitResMapper(
            byteArrayMapper = get(),
            cardCInitResTBSMapper = get()
        )
    }

    factory {
        CardCInitResTBSMapper(
            byteArrayMapper = get(),
            bigIntegerMapper = get()
        )
    }
}
