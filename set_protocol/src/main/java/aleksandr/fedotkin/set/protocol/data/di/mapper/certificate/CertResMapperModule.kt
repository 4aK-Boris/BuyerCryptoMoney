package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.CAMsgMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.CertResDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.CertStatusMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.res.FailedItemMapper
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
