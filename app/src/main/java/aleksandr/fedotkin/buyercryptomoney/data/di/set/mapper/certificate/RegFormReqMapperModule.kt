package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.RegFormReqDataMapper
import org.koin.dsl.module

val regFormReqMapperModule = module {

    factory {
        RegFormReqDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get()
        )
    }
}
