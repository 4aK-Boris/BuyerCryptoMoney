package aleksandr.fedotkin.set.protocol.core.di.repositories

import org.koin.dsl.module

val repositoryTestModule = module {
    includes(coreRepositoryTestModule, generalRepositoryModule, errorRepositoryTestModule)
}