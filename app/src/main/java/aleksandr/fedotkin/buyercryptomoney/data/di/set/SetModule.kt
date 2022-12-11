package aleksandr.fedotkin.buyercryptomoney.data.di.set

import aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto.certificateModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto.keyModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.crypto.signatureModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.buyerCertificateMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.coreMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.errorMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.mapper.generalMapperModule
import aleksandr.fedotkin.buyercryptomoney.data.di.set.repository.setRepositoryModule
import org.koin.dsl.module

val setModule = module {
    includes(
        keyModule,
        signatureModule,
        buyerCertificateMapperModule,
        coreMapperModule,
        errorMapperModule,
        generalMapperModule,
        setRepositoryModule,
        certificateModule
    )
}