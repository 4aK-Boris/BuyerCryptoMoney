package aleksandr.fedotkin.set.protocol.data.di.mapper

import aleksandr.fedotkin.set.protocol.core.DTO
import aleksandr.fedotkin.set.protocol.core.Model
import aleksandr.fedotkin.set.protocol.core.mapper.SetMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EXHMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncKMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.EncXMapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP2Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP3Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEP4Mapper
import aleksandr.fedotkin.set.protocol.data.mappers.crypto.oaep.OAEPMapper
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

    factory {
        EXHMapper(base64Mapper = get())
    }

    factory {
        OAEPMapper(base64Mapper = get(), keyRepository = get(),)
    }

    factory {
        OAEP2Mapper(base64Mapper = get(), keyRepository = get(),)
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        OAEP3Mapper(base64Mapper = get(), keyRepository = get(), mapper = mapper)
    }

    factory { (mapper: SetMapper<Model, DTO>) ->
        OAEP4Mapper(base64Mapper = get(), keyRepository = get(), mapper = mapper)
    }
}