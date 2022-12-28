package aleksandr.fedotkin.set.protocol.data.di.crypto

import java.security.MessageDigest
import org.koin.dsl.module

val messageDigestModule = module {

    single {
        MessageDigest.getInstance("SHA-256")
    }
}