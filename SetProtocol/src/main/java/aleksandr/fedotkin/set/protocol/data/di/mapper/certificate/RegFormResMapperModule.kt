package aleksandr.fedotkin.set.protocol.data.di.mapper.certificate

import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.ReferralDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFieldMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormDataMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormOrReferralMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegFormResTBSMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res.RegTemplateMapper
import org.koin.dsl.module

val regFormResMapperModule = module {

    factory {
        ReferralDataMapper(
            bigIntegerMapper = get(),
            regFieldMapper = get()
        )
    }

    factory {
        RegFieldMapper(bigIntegerMapper = get())
    }

    factory {
        RegFormDataMapper(regTemplateMapper = get())
    }

    factory {
        RegFormOrReferralMapper(
            referralDataMapper = get(),
            regFormDataMapper = get()
        )
    }

    factory {
        RegFormResMapper(
            base64Mapper = get(),
            regFormResTBSMapper = get()
        )
    }

    factory {
        RegFormResTBSMapper(
            bigIntegerMapper = get(),
            base64Mapper  = get(),
            regFormOrReferralMapper = get()
        )
    }

    factory {
        RegTemplateMapper(
            bigIntegerMapper = get(),
            regFieldMapper = get()
        )
    }
}
