package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitReqMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.CardCInitResTBSMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.RegFormReqDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CAMsgMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CertResDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CertStatusMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.FailedItemMapper
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

    factory {
        RegFormReqDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get()
        )
    }

    factory {
        FailedItemMapper()
    }

    factory {
        CAMsgMapper()
    }

    factory {
        CertStatusMapper(
            byteArrayMapper = get(),
            keyRepository = get(),
            failedItemMapper = get(),
            caMsgMapper = get()
        )
    }

    factory {
        CertResDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get(),
            certStatusMapper = get()
        )
    }
}
