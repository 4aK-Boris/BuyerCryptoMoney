package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.certificate.certificateMapperModule
import org.koin.dsl.module

val setMapperModule = module {
    includes(certificateMapperModule, coreMapperModule, errorMapperModule, generalMapperModule)
}
