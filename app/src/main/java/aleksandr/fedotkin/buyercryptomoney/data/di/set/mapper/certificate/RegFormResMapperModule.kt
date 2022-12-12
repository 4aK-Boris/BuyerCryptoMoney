package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.ReferralDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegFieldMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegFormDataMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegFormOrReferralMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegFormResMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegFormResTBSMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.certificate.regformres.RegTemplateMapper
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
            byteArrayMapper = get(),
            regFormResTBSMapper = get()
        )
    }

    factory {
        RegFormResTBSMapper(
            bigIntegerMapper = get(),
            byteArrayMapper = get(),
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
