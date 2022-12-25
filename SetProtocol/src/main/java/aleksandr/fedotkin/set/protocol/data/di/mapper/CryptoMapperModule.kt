package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncKMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncXMapper
import org.koin.dsl.module

val cryptoMapperModule = module {

    factory {
        EncMapper(byteArrayMapper = get())
    }

    factory {
        EncXMapper(byteArrayMapper = get())
    }

    factory {
        EncKMapper(byteArrayMapper = get())
    }
}