package aleksandr.fedotkin.set.protocol.core.di.mappers.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.ReferralDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFieldMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormDataMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormOrReferralMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResTBSMapperTest
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegTemplateMapperTest
import org.koin.dsl.module

val regFormResMapperTestModule = module {

    factory {
        RegFieldMapperTest()
    }

    factory {
        ReferralDataMapperTest()
    }

    factory {
        RegTemplateMapperTest()
    }

    factory {
        RegFormDataMapperTest()
    }

    factory {
        RegFormOrReferralMapperTest()
    }

    factory {
        RegFormResTBSMapperTest()
    }

    factory {
        RegFormResMapperTest()
    }
}