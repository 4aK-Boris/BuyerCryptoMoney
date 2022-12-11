package aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper

import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorMsgMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.ErrorTBSMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.SignedErrorMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.set.error.UnsignedErrorMapper
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