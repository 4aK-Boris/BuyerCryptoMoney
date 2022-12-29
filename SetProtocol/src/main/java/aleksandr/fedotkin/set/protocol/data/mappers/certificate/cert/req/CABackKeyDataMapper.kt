package aleksandr.fedotkin.set.protocol.data.mappers.certificate.cert.req

import aleksandr.fedotkin.set.protocol.data.mappers.core.ByteArrayMapper
import aleksandr.fedotkin.set.protocol.domain.repositories.core.KeyRepository

class CABackKeyDataMapper(
    private val byteArrayMapper: ByteArrayMapper,
    private val keyRepository: KeyRepository
) {

//    @JvmName("map_model_notnull")
//    fun map(model: CABackKeyDataModel): CABackKeyData {
//        return CABackKeyData(
//                caaIgId = model.caaIgId,
//                caKey = byteArrayMapper.map(byteArray = model.caKey.encoded)
//            )
//
//    }
//
//    @JvmName("map_dto_notnull")
//    fun map(dto: CABackKeyData): CABackKeyDataModel {
//        return CABackKeyDataModel(
//                caaIgId = dto.caaIgId,
//                caKey = keyRepository.decodeSecretKey(keyArray = byteArrayMapper.map(string = dto.caKey))
//            )
//
//    }
//
//    @JvmName("map_model_nullable")
//    fun map(model: CABackKeyDataModel?): CABackKeyData? {
//        return model?.let { map(model = it) }
//    }
//
//    @JvmName("map_dto_nullable")
//    fun map(dto: CABackKeyData?): CABackKeyDataModel? {
//        return dto?.let { map(dto = it) }
//    }
}
