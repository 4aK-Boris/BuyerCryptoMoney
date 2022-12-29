package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import org.koin.dsl.module

val cardCInitResMapperModule = module {

    factory {
        CardCInitResMapper(
            base64Mapper = get(),
            cardCInitResTBSMapper = get()
        )
    }

    factory {
        CardCInitResTBSMapper(
            base64Mapper = get(),
            bigIntegerMapper = get()
        )
    }
}
