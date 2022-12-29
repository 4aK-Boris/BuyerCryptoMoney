package aleksandr.fedotkin.set.protocol.data.repositories.certificate.reg.form.req

//class PANOnlyRepositoryImpl(
//    private val panOnlyMapper: PANOnlyMapper,
//    private val jsonMapper: JsonMapper
//) : PANOnlyRepository, BaseRepository() {
//
//    override val serializer = PANOnly.serializer()
//
//    override val convertToModel: (PANOnly) -> PANOnlyModel = panOnlyMapper::map
//
//    override val convertToDTO: (PANOnlyModel) -> PANOnly = panOnlyMapper::map
//
//    override suspend fun createPANOnlyModel(number: String): PANOnlyModel {
//        return PANOnlyModel(
//            pan = BigInteger(number),
//            exNonce = generateNewNumber()
//        )
//    }
//}
