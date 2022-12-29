package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncK
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncKModel

class EncKMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

//    fun map(model: EncKModel): EncK {
//        return EncK(
//            signature = byteArrayMapper.map(byteArray = model.signature),
//            data = byteArrayMapper.map(byteArray = model.data)
//        )
//    }
//
//    fun map(dto: EncK): EncKModel {
//        return EncKModel(
//            signature = byteArrayMapper.map(string = dto.signature),
//            data = byteArrayMapper.map(string  = dto.data)
//        )
//    }
}