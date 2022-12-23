package aleksandr.fedotkin.set.protocol.core.di

import aleksandr.fedotkin.set.protocol.data.di.crypto.cryptoModule
import aleksandr.fedotkin.set.protocol.data.di.mapper.mappersModule
import aleksandr.fedotkin.set.protocol.data.di.networkModule
import aleksandr.fedotkin.set.protocol.data.di.repository.repositoriesModule
import aleksandr.fedotkin.set.protocol.domain.di.useCasesModule
import org.koin.dsl.module

val setModule = module {
    includes(repositoriesModule, mappersModule, cryptoModule, useCasesModule, networkModule)
}