package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.AcctDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.AcquirerIDMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.CABackKeyDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.CertReqDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.IDDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.MerchantAcquirerIDMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.PANData0Mapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.PublicKeySorEMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.certreq.RegFormItemsMapper
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
            regFormItemsMapper = get(),
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
            keyRepository = get()
        )
    }

    factory {
        RegFormItemsMapper()
    }
}
