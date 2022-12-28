package aleksandr.fedotkin.set.protocol.core.di

import aleksandr.fedotkin.set.protocol.core.TestMapper
import aleksandr.fedotkin.set.protocol.core.di.mappers.mapperTestModule
import aleksandr.fedotkin.set.protocol.core.di.repositories.repositoryTestModule
import aleksandr.fedotkin.set.protocol.core.repository.TestRepository
import aleksandr.fedotkin.set.protocol.core.repository.TestRepositoryImpl
import org.koin.dsl.module

val testModule = module {

    includes(mapperTestModule, repositoryTestModule)

    factory {
        TestMapper(bigIntegerMapper = get(), base64Mapper = get())
    }

    factory<TestRepository> {
        TestRepositoryImpl(mapper = get())
    }
}