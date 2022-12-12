package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResTBSMapper
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
