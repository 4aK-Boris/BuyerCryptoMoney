package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMapper
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorMsgMapper
import aleksandr.fedotkin.set.protocol.data.mappers.error.ErrorTBSMapper
import aleksandr.fedotkin.set.protocol.data.mappers.error.SignedErrorMapper
import aleksandr.fedotkin.set.protocol.data.mappers.error.UnsignedErrorMapper
import org.koin.dsl.module

val errorMapperModule = module {

    factory {
        ErrorMapper(
            signedErrorMapper = get(),
            unsignedErrorMapper = get()
        )
    }

    factory {
        ErrorTBSMapper(
            byteArrayMapper = get(),
            bigIntegerMapper = get(),
            errorMsgMapper = get()
        )
    }

    factory {
        ErrorMsgMapper(
            messageHeaderMapper = get()
        )
    }

    factory {
        SignedErrorMapper(
            byteArrayMapper = get()
        )
    }

    factory {
        UnsignedErrorMapper(errorTBSMapper = get())
    }
}