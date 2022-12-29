package aleksandr.fedotkin.set.protocol.core.di.mappers.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqMapperTest
import org.koin.dsl.module

val regFormReqMapperTestModule = module {

    factory {
        RegFormReqMapperTest()
    }

    factory {
        RegFormReqDataMapperTest()
    }

    factory {
        PANOnlyMapperTest()
    }
}