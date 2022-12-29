package aleksandr.fedotkin.set.protocol.data.di.repository

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP2Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP3Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP4Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEPMapper
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.EXHRepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP2RepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP3RepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEP4RepositoryImpl
import aleksandr.fedotkin.set.protocol.data.repositories.crypto.OAEPRepositoryImpl
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.EXHRepository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP2Repository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP3Repository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEP4Repository
import aleksandr.fedotkin.set.protocol.domain.repositories.crypto.OAEPRepository
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val cryptoRepositoriesModule = module {

    factory<OAEPRepository> {
        val oaepMapper = get<OAEPMapper>()
        OAEPRepositoryImpl(mapper = get(), cipherRepository = get { parametersOf(oaepMapper) })
    }

    factory<OAEP2Repository> {
        val oaepMapper = get<OAEP2Mapper>()
        OAEP2RepositoryImpl(mapper = get(), cipherRepository = get { parametersOf(oaepMapper) })
    }

    factory<OAEP3Repository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        val oaepMapper = get<OAEP3Mapper<Model, DTO>> { parametersOf(mapper) }
        OAEP3RepositoryImpl(
            mapper = oaepMapper,
            cipherRepository = get { parametersOf(oaepMapper) })
    }

    factory<OAEP4Repository<Model, DTO>> { (mapper: SetMapper<Model, DTO>) ->
        val oaepMapper = get<OAEP4Mapper<Model, DTO>> { parametersOf(mapper) }
        OAEP4RepositoryImpl(
            mapper = oaepMapper,
            cipherRepository = get { parametersOf(oaepMapper) })
    }

    factory<EXHRepository<Model, Model>> { (mapper: SetMapper<Model, DTO>, secondaryMapper: SetMapper<Model, DTO>) ->
        EXHRepositoryImpl<Model, Model, DTO, DTO>(
            mapper = get(),
            jsonMapper = get { parametersOf(mapper) },
            secondJsonMapper = get { parametersOf(secondaryMapper) },
            cipherRepository = get { parametersOf(mapper) },
            keyRepository = get(),
            messageDigestRepository = get { parametersOf(mapper) },
            oaepRepository = get { parametersOf(secondaryMapper) }
        )
    }
}