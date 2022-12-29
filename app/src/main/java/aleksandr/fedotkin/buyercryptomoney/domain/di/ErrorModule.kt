package aleksandr.fedotkin.buyercryptomoney.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val errorModule = module {
    singleOf(::ErrorHandler)
}