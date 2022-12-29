package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.AcctDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.AcquirerIDMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.CABackKeyDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.CertReqDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.CertReqMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.IDDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.MerchantAcquirerIDMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.PANData0Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req.PublicKeySorEMapper
import org.koin.dsl.module

val certReqMapperModule = module {

    factory {
        AcctDataMapper(bigIntegerMapper = get())
    }

    factory {
        AcquirerIDMapper(bigIntegerMapper = get())
    }

    factory {
        CABackKeyDataMapper(
            byteArrayMapper = get(),
            keyRepository = get()
        )
    }

    factory {
        CertReqDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get(),
            dateTimeMapper = get(),
            idDataMapper = get(),
            publicKeySorEMapper = get(),
            caBackKeyDataMapper = get()
        )
    }

    factory {
        IDDataMapper(
            merchantAcquirerIDMapper = get(),
            acquirerIDMapper = get()
        )
    }

    factory {
        MerchantAcquirerIDMapper(bigIntegerMapper = get())
    }

    factory {
        PANData0Mapper(
            bigIntegerMapper = get(),
            dateTimeMapper = get()
        )
    }

    factory {
        PublicKeySorEMapper(
            byteArrayMapper = get(),
        )
    }

    factory {
        //RegFormItemsMapper()
    }

    factory {
        CertReqMapper(
            encMapper = get(),
            encXMapper = get()
        )
    }
}
