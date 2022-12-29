package aleksandr.fedotkin.core.di

import aleksandr.fedotkin.core.exception.ErrorHandler
import org.koin.dsl.module

val coreModule = module {
    single {
        ErrorHandler()
    }
}