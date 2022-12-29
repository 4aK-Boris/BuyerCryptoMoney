package aleksandr.fedotkin.set.protocol.data.repositories.crypto

//class OAEP2RepositoryImpl(
//    private val oaepMapper: OAEPMapper,
//    private val cipherRepository: CipherRepository
//) : OAEP2Repository {
//
//    override fun <T : Model, R : DTO> convertToModel(
//        oaep2: OAEP2,
//        map: (R) -> T,
//        serializer: KSerializer<R>
//    ): OAEP2Model<T> {
//        return oaepMapper.map(dto = oaep2, map = map, serializer = serializer)
//    }
//
//    override fun <T : Model, R : DTO> convertToDTO(
//        oaep2Model: OAEP2Model<T>,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): OAEP2 {
//        return oaepMapper.map(model = oaep2Model, map = map, serializer = serializer)
//    }
//
//    override suspend fun <T : Model, R : DTO> createAndEncrypt(
//        secretKey: SecretKey,
//        data: T,
//        certificate: X509Certificate,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): ByteArray {
//        return encrypt(
//            model = OAEP2Model(secretKey = secretKey, p = data),
//            certificate = certificate,
//            map = map,
//            serializer = serializer
//        )
//    }
//
//    override suspend fun <T : Model, R : DTO> encrypt(
//        model: OAEP2Model<T>,
//        certificate: X509Certificate,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): ByteArray {
//        return encrypt(
//            model = model,
//            publicKey = certificate.publicKey,
//            serializer = serializer,
//            map = map
//        )
//    }
//
//    override suspend fun <T : Model, R : DTO> encrypt(
//        model: OAEP2Model<T>,
//        publicKey: PublicKey,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): ByteArray {
//        return cipherRepository.asymmetricEncrypt(
//            model = model,
//            serializer = OAEP2.serializer(),
//            publicKey = publicKey
//        ) {
//            convertToDTO(oaep2Model = it, map = map, serializer = serializer)
//        }
//    }
//
//    override suspend fun <T : Model, R : DTO> decrypt(
//        data: ByteArray,
//        privateKey: PrivateKey,
//        map: (R) -> T,
//        serializer: KSerializer<R>
//    ): OAEP2Model<T> {
//        return cipherRepository.asymmetricDecrypt(
//            data = data,
//            privateKey = privateKey,
//            deserializer = OAEP2.serializer()
//        ) {
//            convertToModel(oaep2 = it, map = map, serializer = serializer)
//        }
//    }
//}