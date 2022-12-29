package aleksandr.fedotkin.set.protocol.data.di.repository

import aleksandr.fedotkin.set.protocol.core.di.certificate.Certificate
import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResTBSMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.PANOnlyMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.req.RegFormReqDataMapper
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.req.CardCInitReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.card.c.init.res.CardCInitResTBSRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.PANOnlyRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqDataRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req.RegFormReqRepositoryImpl
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.req.CardCInitReqRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.card.c.init.res.CardCInitResTBSRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.PANOnlyRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqDataRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.certificate.reg.form.req.RegFormReqRepository
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val certificateRepositoryModule = module {

    factory<CardCInitReqRepository> { CardCInitReqRepositoryImpl(mapper = get()) }

    factory<CardCInitResTBSRepository> {
        CardCInitResTBSRepositoryImpl(
            mapper = get(),
            keyRepository = get(),
            privateKey = get(qualifier = named(CertificateQualifiers.CCA)),
            certificate = get<Certificate>(qualifier = named(CertificateQualifiers.CCA)).x509Certificate,
            signatureRepository = get { parametersOf(get<CardCInitResTBSMapper>()) }
        )
    }

    factory<CardCInitResRepository> {
        CardCInitResRepositoryImpl(
            mapper = get(),
            cardCInitResTBSRepository = get()
        )
    }

    factory<PANOnlyRepository> {
        PANOnlyRepositoryImpl(mapper = get())
    }

    factory<RegFormReqDataRepository> {
        RegFormReqDataRepositoryImpl(mapper = get())
    }

    factory<RegFormReqRepository> {
        RegFormReqRepositoryImpl(
            mapper = get(),
            panOnlyRepository = get(),
            regFormReqDataRepository = get(),
            exhRepository = get { parametersOf(get<RegFormReqDataMapper>(), get<PANOnlyMapper>()) }
        )
    }
}