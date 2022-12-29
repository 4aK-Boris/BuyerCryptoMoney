package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.res

//class RegFormResRepositoryImpl(
//    private val regFormResMapper: RegFormResMapper,
//    private val signatureRepository: SignatureRepository,
//    private val keyRepository: KeyRepository
//) : RegFormResRepository {
//
//    override val serializer = RegFormRes.serializer()
//
//    override val convertToModel: (RegFormRes) -> RegFormResModel = regFormResMapper::map
//
//    override val convertToDTO: (RegFormResModel) -> RegFormRes  = regFormResMapper::map
//
//    override suspend fun checkSignature(regFormResModel: RegFormResModel): Boolean {
//        return signatureRepository.verifySignature(
//            data = convertToDTO(regFormResModel).regFormResTBS,
//            serializer = RegFormResTBS.serializer(),
//            publicKey = keyRepository.decodeCertificate(certificate = regFormResModel.regFormResTBS.caeThumb).publicKey,
//            signatureArray = regFormResModel.ca
//        )
//    }
//}