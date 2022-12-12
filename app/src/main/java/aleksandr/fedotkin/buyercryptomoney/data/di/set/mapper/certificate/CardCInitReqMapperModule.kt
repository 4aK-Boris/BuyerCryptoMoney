package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitReqMapper
import org.koin.dsl.module

val cardCInitReqMapperModule = module {

    factory {
        CardCInitReqMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get()
        )
    }
}
