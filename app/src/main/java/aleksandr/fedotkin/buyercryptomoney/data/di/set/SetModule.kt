package aleksandr.fedotkin.buyercryptomoney.data.di.set

import aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto.cryptoModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.setMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.repository.setRepositoryModule
import org.koin.dsl.module

val setModule = module {
    includes(cryptoModule, setMapperModule, setRepositoryModule)
}
