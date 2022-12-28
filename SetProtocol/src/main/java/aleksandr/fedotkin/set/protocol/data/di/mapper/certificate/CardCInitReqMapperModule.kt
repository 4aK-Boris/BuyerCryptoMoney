package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import org.koin.dsl.module

val cardCInitReqMapperModule = module {

    factory {
        CardCInitReqMapper(
            bigIntegerMapper = get(),
            base64Mapper = get()
        )
    }
}
