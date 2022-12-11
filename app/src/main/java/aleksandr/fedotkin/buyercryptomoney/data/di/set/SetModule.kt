package aleksandr.fedotkin.buyercryptomoney.data.di.set

import aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto.cryptoModule
import org.koin.dsl.module
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.setMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.repository.setRepositoryModule

val setModule = module {
    includes(cryptoModule, setMapperModule, setRepositoryModule)
}