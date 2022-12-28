package aleksandr.fedotkin.set.protocol.data.repositories.crypto

//class EXHRepositoryImpl(
//    private val jsonMapper: JsonMapper,
//    private val cipherRepository: CipherRepository,
//    private val keyRepository: KeyRepository,
//    private val messageDigestRepository: MessageDigestRepository,
//    private val oaepRepository: OAEP3Repository
//) : EXHRepository {
//
//    override suspend fun <T : Model, S : Model, R : DTO, K : DTO> encrypt(
//        publicKey: PublicKey,
//        data: T,
//        secondaryData: S,
//        map: (T) -> R,
//        secondaryMap: (S) -> K,
//        serializer: KSerializer<R>,
//        secondarySerializer: KSerializer<K>
//    ): EXHModel {
//        val dataArray = dataModelToByteArray(data = data, map = map, serializer = serializer)
//        val secondaryDataArray = dataModelToByteArray(
//            data = secondaryData,
//            map = secondaryMap,
//            serializer = secondarySerializer
//        )
//        val secretKey = generateSecretKey()
//        val cipherSharedData =
//            encryptSharedData(
//                data = dataArray,
//                secondaryData = secondaryDataArray,
//                secretKey = secretKey
//            )
//        val cipherOAEP = oaepRepository.createAndEncryptOAEPModel(
//            secretKey = secretKey,
//            hash = messageDigestRepository.messageDigest(data = dataArray),
//            p = secondaryData,
//            map = secondaryMap,
//            serializer = secondarySerializer,
//            publicKey = publicKey
//        )
//        return EXHModel(data = cipherSharedData, secretKey = cipherOAEP)
//    }
//
//    override suspend fun <T : Model, S : Model, R : DTO, K : DTO> decrypt(
//        privateKey: PrivateKey,
//        model: EXHModel,
//        map: (R) -> T,
//        secondaryMap: (K) -> S,
//        reverseSecondaryMap: (S) -> K,
//        serializer: KSerializer<R>,
//        secondarySerializer: KSerializer<K>
//    ): Pair<T, S> {
//        val oaepModel = oaepRepository.decryptOAEPModel(
//            cipherOAEP = model.secretKey,
//            privateKey = privateKey,
//            serializer = secondarySerializer,
//            map = secondaryMap
//        )
//        val secondaryData = dataModelToByteArray(
//            data = oaepModel.p,
//            map = reverseSecondaryMap,
//            serializer = secondarySerializer
//        )
//        val clearSharedData = cipherRepository.symmetricDecrypt(
//            data = model.data,
//            secretKey = oaepModel.secretKey
//        )
//        val dataModel = byteArrayToDataModel(
//            byteArray = clearSharedData.take(clearSharedData.size - secondaryData.size)
//                .toByteArray(), map = map, serializer = serializer
//        )
//        return dataModel to oaepModel.p
//    }
//
//    private suspend fun encryptSharedData(
//        data: ByteArray,
//        secondaryData: ByteArray,
//        secretKey: SecretKey
//    ): ByteArray {
//        return cipherRepository.symmetricEncrypt(data = data + secondaryData, secretKey = secretKey)
//    }
//
//    private suspend fun generateSecretKey(): SecretKey {
//        return keyRepository.generateSecretKey()
//    }
//
//    private fun <T : Model, R : DTO> dataModelToByteArray(
//        data: T,
//        map: (T) -> R,
//        serializer: KSerializer<R>
//    ): ByteArray {
//        return jsonMapper.dtoToByteArray(data = map(data), serializer = serializer)
//    }
//
//    private fun <T : Model, R : DTO> byteArrayToDataModel(
//        byteArray: ByteArray,
//        map: (R) -> T,
//        serializer: KSerializer<R>
//    ): T {
//        return map(jsonMapper.byteArrayToDTO(data = byteArray, serializer = serializer))
//    }
//}
