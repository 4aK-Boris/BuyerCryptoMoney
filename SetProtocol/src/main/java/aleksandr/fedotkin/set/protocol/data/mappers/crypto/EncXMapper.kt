package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.EncX
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncXModel

class EncXMapper(private val byteArrayMapper: ByteArrayMapper) {

//    fun map(model: EncXModel): EncX {
//        return EncX(
//            signature = byteArrayMapper.map(byteArray = model.signature),
//            data = byteArrayMapper.map(byteArray = model.data),
//            secretKeyAndData = byteArrayMapper.map(byteArray = model.secretKeyAndData),
//        )
//    }
//
//    fun map(dto: EncX): EncXModel {
//        return EncXModel(
//            signature = byteArrayMapper.map(string = dto.signature),
//            data = byteArrayMapper.map(string = dto.data),
//            secretKeyAndData = byteArrayMapper.map(string = dto.secretKeyAndData),
//        )
//    }
}