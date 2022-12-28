package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqMapper
import org.koin.dsl.module

val regFormReqMapperModule = module {

    factory {
        RegFormReqDataMapper(
            bigIntegerMapper = get(),
            base64Mapper = get()
        )
    }

    factory {
        RegFormReqMapper(exhMapper = get())
    }

    factory {
        PANOnlyMapper(bigIntegerMapper = get())
    }
}
