package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

//class RegFormReqRepositoryImpl(
//    private val panOnlyRepository: PANOnlyRepository,
//    private val exhRepository: EXHRepository,
//    private val regFormReqDataMapper: RegFormReqDataMapper,
//    private val keyRepository: KeyRepository
//) : RegFormReqRepository, BaseRepository() {

//    override val serializer = RegFormReqData.serializer()
//
//    override val convertToDTO: (RegFormReqDataModel) -> RegFormReqData = regFormReqDataMapper::map
//
//    override val convertToModel: (RegFormReqData) -> RegFormReqDataModel = regFormReqDataMapper::map
//
//    override suspend fun createRegFormReqDataModel(
//        lidEE: BigInteger,
//        lidCA: BigInteger
//    ): Pair<RegFormReqDataModel, BigInteger> {
//        return generateNewNumber().let { rrpid ->
//            RegFormReqDataModel(
//                rrpID = rrpid,
//                lidEE = lidEE,
//                challEE2 = generateNewNumber(),
//                lidCA = lidCA,
//                requestType = RequestType.SIGNATURE,
//                language = Language.RUSSIAN,
//                thumbs = emptyList()
//            ) to rrpid
//        }
//    }
//
//    override suspend fun createCryptoDataModel(
//        number: String,
//        lidEE: BigInteger,
//        lidCA: BigInteger,
//        caeThumb: ByteArray
//    ): Pair<CryptoDataModel, BigInteger> {
//        return createRegFormReqDataModel(lidEE = lidEE, lidCA = lidCA)
//            .let { (regFormReqDataModel, rrpid) ->
//                exhRepository.encrypt(
//                    publicKey = keyRepository.decodeCertificate(certificate = caeThumb).publicKey,
//                    data = regFormReqDataModel,
//                    secondaryData = panOnlyRepository.createPANOnlyModel(number = number),
//                    map = convertToDTO,
//                    secondaryMap = panOnlyRepository.convertToDTO,
//                    serializer = serializer,
//                    secondarySerializer = panOnlyRepository.serializer
//                ) to rrpid
//            }
//    }
//}
