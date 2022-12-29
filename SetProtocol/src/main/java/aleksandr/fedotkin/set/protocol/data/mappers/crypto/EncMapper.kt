package aleksandr.fedotkin.set.protocol.data.mappers.crypto

import aleksandr.fedotkin.set.protocol.data.dto.crypto.Enc
import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.models.crypto.EncModel

class EncMapper(
    private val byteArrayMapper: ByteArrayMapper
) {

//    fun map(model: EncModel): Enc {
//        return Enc(
//            signature = byteArrayMapper.map(byteArray = model.signature),
//            secretKey = byteArrayMapper.map(byteArray = model.secretKey),
//            data = byteArrayMapper.map(byteArray = model.data)
//        )
//    }
//
//    fun map(dto: Enc): EncModel {
//        return EncModel(
//            signature = byteArrayMapper.map(string = dto.signature),
//            secretKey = byteArrayMapper.map(string  = dto.secretKey),
//            data = byteArrayMapper.map(string  = dto.data)
//        )
//    }
}
