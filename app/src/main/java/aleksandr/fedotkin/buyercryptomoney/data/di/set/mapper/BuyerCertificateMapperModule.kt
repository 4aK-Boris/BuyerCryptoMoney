package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitReqMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResTBSMapper
import org.koin.dsl.module

val buyerCertificateMapperModule = module {

    factory {
        CardCInitReqMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get()
        )
    }

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