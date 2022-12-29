package aleksandr.fedotkin.set.protocol.domain.repositories.crypto

//interface OAEP3Repository {
//
//    suspend fun <T: Model> createOAEPModel(
//        secretKey: SecretKey,
//        hash: ByteArray,
//        p: T
//    ): OAEP3Model<T>
//
//    suspend fun <T: Model, R: DTO> createAndEncryptOAEPModel(
//        secretKey: SecretKey,
//        hash: ByteArray,
//        p: T,
//        map: (T) -> R,
//        serializer: KSerializer<R>,
//        publicKey: PublicKey
//    ): ByteArray
//
//    suspend fun <T: Model, R: DTO> encryptOAEPModel(
//        oaepModel: OAEP3Model<T>,
//        map: (T) -> R,
//        serializer: KSerializer<R>,
//        publicKey: PublicKey
//    ): ByteArray
//
//    suspend fun <T: Model, R: DTO> decryptOAEPModel(
//        cipherOAEP: ByteArray,
//        privateKey: PrivateKey,
//        serializer: KSerializer<R>,
//        map: (R) -> T
//    ): OAEP3Model<T>
//
//    fun <T: Model, R: DTO> convertToModel(
//        oaep: OAEP3,
//        map: (R) -> T,
//        serializer: KSerializer<R>
//    ): OAEP3Model<T>
//
//    fun <T: Model, R: DTO> convertToDTO(
//        oaepModel: OAEP3Model<T>,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): OAEP3
//}
