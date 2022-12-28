package aleksandr.fedotkin.set.protocol.data.repositories.crypto

//class EncRepositoryImpl(
//    private val encMapper: EncMapper,
//    private val cipherRepository: CipherRepository,
//    private val signatureRepository: SignatureRepository,
//    private val keyRepository: KeyRepository
//) : EncRepository {
//
//    override val serializer = Enc.serializer()
//
//    override val convertToDTO: (EncModel) -> Enc = encMapper::map
//
//    override val convertToModel: (Enc) -> EncModel = encMapper::map
//
//    override suspend fun <T : Model, R : DTO> encrypt(
//        model: T,
//        map: (T) -> R,
//        serializer: KSerializer<R>,
//        certificate: X509Certificate,
//        privateKey: PrivateKey
//    ): EncModel {
//        return encrypt(
//            model = model,
//            map = map,
//            serializer = serializer,
//            publicKey = certificate.publicKey,
//            privateKey = privateKey
//        )
//    }
//
//    override suspend fun <T : Model, R : DTO> encrypt(
//        model: T,
//        map: (T) -> R,
//        serializer: KSerializer<R>,
//        publicKey: PublicKey,
//        privateKey: PrivateKey
//    ): EncModel {
//        val signature = signatureRepository.createSignature(
//            model = model,
//            map = map,
//            serializer = serializer,
//            privateKey = privateKey
//        )
//        val secretKey = keyRepository.generateSecretKey()
//        val cipherData = cipherRepository.symmetricEncrypt(
//            model = model,
//            map = map,
//            serializer = serializer,
//            secretKey = secretKey
//        )
//        val cipherSecretKey =
//            cipherRepository.asymmetricEncrypt(data = secretKey.encoded, publicKey = publicKey)
//        return EncModel(
//            signature = signature,
//            secretKey = cipherSecretKey,
//            data = cipherData
//        )
//    }
//
//    override suspend fun <T : Model, R : DTO> decrypt(
//        enc: Enc,
//        map: (R) -> T,
//        serializer: KSerializer<R>,
//        publicKey: PublicKey,
//        privateKey: PrivateKey
//    ): T {
//        val encModel = convertToModel(enc)
//        val secretKey = keyRepository.decodeSecretKey(
//            keyArray = cipherRepository.asymmetricDecrypt(
//                data = encModel.secretKey,
//                privateKey = privateKey
//            )
//        )
//        val dto = cipherRepository.symmetricDecrypt(
//            data = encModel.data,
//            deserializer = serializer,
//            secretKey = secretKey
//        )
//        if (!signatureRepository.verifySignature(
//                data = dto,
//                serializer = serializer,
//                publicKey = publicKey,
//                signatureArray = encModel.signature
//            )
//        ) {
//            throw SignatureFailure()
//        }
//        return map(dto)
//    }
//}