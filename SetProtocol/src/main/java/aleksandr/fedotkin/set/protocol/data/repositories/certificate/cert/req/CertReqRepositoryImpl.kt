package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.req


//class CertReqRepositoryImpl(
//    private val keyRepository: KeyRepository,
//    private val certReqDataRepository: CertReqDataRepository,
//    private val panData0Repository: PanData0Repository,
//    private val privateKey: PrivateKey,
//    private val encRepository: EncRepository,
//    private val encXRepository: EncXRepository,
//    private val certReqMapper: CertReqMapper
//) : CertReqRepository, BaseRepository() {
//
//    override val serializer = CertReq.serializer()
//
//    override val convertToDTO: (CertReqModel) -> CertReq = certReqMapper::map
//
//    override val convertToModel: (CertReq) -> CertReqModel = certReqMapper::map
//
//    override suspend fun createCertReqModel(
//        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
//        data: List<String>
//    ): Pair<CertReqModel, BigInteger> {
//        val rrpid = generateNewNumber()
//        val secretKey = keyRepository.generateSecretKey()
//        val certReqDataModel = certReqDataRepository.createCertReqData(
//            messageWrapperModel = messageWrapperModel,
//            data = data,
//            secretKey = secretKey,
//            rrpid = rrpid
//        )
//        val certificate =
//            keyRepository.decodeCertificate(certificate = messageWrapperModel.messageModel.regFormResTBS.caeThumb)
//        val panDataModel =
//            panData0Repository.createPANData(month = data[1], year = data[2], number = data[0])
//        return createCertReq(
//            model = certReqDataModel,
//            panDataModel = panDataModel,
//            certificate = certificate
//        ) to rrpid
//    }
//
//    private suspend fun createCertReq(
//        model: CertReqDataModel,
//        panDataModel: PANData0Model,
//        certificate: X509Certificate
//    ): CertReqModel {
//        return CertReqModel(
//            enc = createEnc(model = model, certificate = certificate),
//            encX = createEncX(model = model, certificate = certificate, panDataModel = panDataModel)
//        )
//    }
//
//    private suspend fun createEnc(model: CertReqDataModel, certificate: X509Certificate): EncModel {
//        return encRepository.encrypt(
//            model = model,
//            map = certReqDataRepository.convertToDTO,
//            serializer = CertReqData.serializer(),
//            certificate = certificate,
//            privateKey = privateKey
//        )
//    }
//
//    private suspend fun createEncX(
//        model: CertReqDataModel,
//        panDataModel: PANData0Model,
//        certificate: X509Certificate
//    ): EncXModel {
//        return encXRepository.encrypt(
//            data = model,
//            secondaryData = panDataModel,
//            map = certReqDataRepository.convertToDTO,
//            secondaryMap = panData0Repository.convertToDTO,
//            serializer = certReqDataRepository.serializer,
//            secondarySerializer = panData0Repository.serializer,
//            certificate = certificate,
//            privateKey = privateKey
//        )
//    }
//}