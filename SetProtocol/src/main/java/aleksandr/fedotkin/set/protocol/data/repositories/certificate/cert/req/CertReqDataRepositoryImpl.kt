package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.req

//class CertReqDataRepositoryImpl(
//    private val certReqDataMapper: CertReqDataMapper,
//    private val publicKey: PublicKey
//): CertReqDataRepository, BaseRepository() {
//
//    override val serializer = CertReqData.serializer()
//
//    override val convertToDTO: (CertReqDataModel) -> CertReqData = certReqDataMapper::map
//
//    override val convertToModel: (CertReqData) -> CertReqDataModel = certReqDataMapper::map
//
//    override suspend fun createCertReqData(
//        messageWrapperModel: MessageWrapperModel<RegFormResModel>,
//        data: List<String>,
//        rrpid: BigInteger,
//        secretKey: SecretKey
//    ): CertReqDataModel {
//        with(messageWrapperModel.messageModel.regFormResTBS) {
//            return CertReqDataModel(
//                rrpID = rrpid,
//                lidEE = lidEE,
//                challCA = challCA,
//                challEE3 = generateNewNumber(),
//                lidCA = lidCA,
//                requestType = requestType,
//                idData = null,
//                regFormID = regFormOrReferral.referralData.regFormID,
//                regForm = createRegForm(
//                    regFieldSeq = regFormOrReferral.referralData.regFieldSeq,
//                    data = data
//                ),
//                caBackKeyData = CABackKeyDataModel(caaIgId = CIPHER_ALGORITHM, caKey = secretKey),
//                publicKeySorE = PublicKeySorEModel(publicKeyE = null, publicKeyS = publicKey),
//                eeThumb = byteArrayOf(),
//                thumbs = thumbs,
//                requestDate = dateTime
//            )
//        }
//    }
//
//    private fun createRegForm(
//        regFieldSeq: List<RegFieldModel>,
//        data: List<String>
//    ): List<RegFormItemsModel> {
//        return regFieldSeq.zip(data).map { (regFieldModel, value) ->
//            RegFormItemsModel(
//                fieldName = regFieldModel.fieldName.first(),
//                fieldValue = value
//            )
//        }
//    }
//
//    private val dateTime: LocalDateTime
//        get() = LocalDateTime.now()
//}