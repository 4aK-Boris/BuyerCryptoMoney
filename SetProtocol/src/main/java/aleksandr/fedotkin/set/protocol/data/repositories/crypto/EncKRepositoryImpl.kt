package aleksandr.fedotkin.set.protocol.data.repositories.crypto

//class EncKRepositoryImpl(
//    private val encKMapper: EncKMapper,
//    private val signatureRepository: SignatureRepository,
//    private val cipherRepository: CipherRepository
//) : EncKRepository {
//
//    override val serializer = EncK.serializer()
//
//    override val convertToDTO: (EncKModel) -> EncK = encKMapper::map
//
//    override val convertToModel: (EncK) -> EncKModel = encKMapper::map
//
//    override suspend fun <T : Model, R : DTO> encrypt(
//        data: T,
//        secretKey: SecretKey,
//        privateKey: PrivateKey,
//        serializer: KSerializer<R>,
//        map: (T) -> R
//    ): EncKModel {
//        val signature = signatureRepository.createSignature(
//            model = data,
//            map = map,
//            serializer = serializer,
//            privateKey = privateKey
//        )
//        val cipherData = cipherRepository.symmetricEncrypt(
//            model = data,
//            map = map,
//            serializer = serializer,
//            secretKey = secretKey
//        )
//        return EncKModel(signature = signature, data = cipherData)
//    }
//
//    override suspend fun <T : Model, R : DTO> decrypt(
//        model: EncKModel,
//        secretKey: SecretKey,
//        certificate: X509Certificate,
//        serializer: KSerializer<R>,
//        map: (R) -> T
//    ): T {
//        return decrypt(
//            model = model,
//            secretKey = secretKey,
//            publicKey = certificate.publicKey,
//            serializer = serializer,
//            map = map
//        )
//    }
//
//    override suspend fun <T : Model, R : DTO> decrypt(
//        model: EncKModel,
//        secretKey: SecretKey,
//        publicKey: PublicKey,
//        serializer: KSerializer<R>,
//        map: (R) -> T
//    ): T {
//        val data = cipherRepository.symmetricDecrypt(
//            data = model.data,
//            deserializer = serializer,
//            secretKey = secretKey
//        )
//        if (!signatureRepository.verifySignature(
//                data = data,
//                serializer = serializer,
//                publicKey = publicKey,
//                signatureArray = model.signature
//            )
//        ) {
//            throw SignatureFailure()
//        }
//        return map(data)
//    }
//}