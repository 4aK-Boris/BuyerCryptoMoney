package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import org.koin.dsl.module

val regFormReqMapperModule = module {

    factory {
        RegFormReqDataMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get()
        )
    }
}
