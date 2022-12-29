package aleksandr.fedotkin.set.protocol.domain.di

import aleksandr.fedotkin.set.protocol.core.di.certificate.CertificateQualifiers
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.req.CardCInitReqMapper
import aleksandr.fedotkin.set.protocol.data.mappers.certificate.card.c.init.res.CardCInitResMapper
import aleksandr.fedotkin.set.protocol.domain.useceses.BuyerCertificateUSeCase
import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitReqClientUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.client.CardCInitResClientUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.server.CardCInitReqServerUseCase
import aleksandr.fedotkin.set.protocol.domain.useceses.server.CardCInitResServerUseCase
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {

    factory {
        BuyerCertificateUSeCase(
            cardCInitReqUseCase = get(),
            cardCInitResUseCase = get()
        )
    }

    factory {
        CardCInitReqClientUseCase(
            repository = get(),
            privateKey = get(),
            publicKey = get(),
            messageWrapperRepository = get {
                parametersOf(get<CardCInitReqMapper>())
            })
    }

    factory {
        CardCInitReqServerUseCase(
            cardCInitReqRepository = get(),
            privateKey = get(
                qualifier = named(
                    CertificateQualifiers.CCA
                )
            ), publicKey = get(
                qualifier = named(
                    CertificateQualifiers.CCA
                )
            ),
            messageWrapperRepository = get {
                parametersOf(get<CardCInitReqMapper>())
            }
        )
    }

    factory {
        CardCInitResClientUseCase(
            repository = get(),
            publicKey = get(),
            privateKey = get(),
            messageWrapperRepository = get {
                parametersOf(get<CardCInitResMapper>())
            })
    }

    factory {
        CardCInitResServerUseCase(
            repository = get(),
            privateKey = get(
                qualifier = named(
                    CertificateQualifiers.CCA
                )
            ), publicKey = get(
                qualifier = named(
                    CertificateQualifiers.CCA
                )
            ),
            messageWrapperRepository = get {
                parametersOf(get<CardCInitResMapper>())
            }
        )
    }
}