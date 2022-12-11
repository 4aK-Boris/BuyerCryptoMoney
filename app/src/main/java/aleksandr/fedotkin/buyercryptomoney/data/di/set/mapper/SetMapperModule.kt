package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import org.koin.dsl.module

val setMapperModule = module {
    includes(buyerCertificateMapperModule, coreMapperModule, errorMapperModule, generalMapperModule)
}