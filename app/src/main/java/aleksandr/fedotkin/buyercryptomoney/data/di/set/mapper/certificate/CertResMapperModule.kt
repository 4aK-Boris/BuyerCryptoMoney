package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CAMsgMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CertResDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.CertStatusMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certres.FailedItemMapper
import org.koin.dsl.module

val certResMapperModule = module {

    factory {
        CAMsgMapper()
    }

    factory {
        CertResDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get(),
            certStatusMapper = get()
        )
    }

    factory {
        CertStatusMapper(
            byteArrayMapper = get(),
            keyRepository = get(),
            caMsgMapper = get(),
            failedItemMapper = get()
        )
    }

    factory {
        FailedItemMapper()
    }
}
