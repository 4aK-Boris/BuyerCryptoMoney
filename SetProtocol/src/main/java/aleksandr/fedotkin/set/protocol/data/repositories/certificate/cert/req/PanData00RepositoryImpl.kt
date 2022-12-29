package aleksandr.fedotkin.set.protocol.data.repositories.certificate.cert.req

//class PanData00RepositoryImpl(
//    private val panData0Mapper: PANData0Mapper
//): PanData0Repository, BaseRepository() {
//
//    override val serializer = PANData0.serializer()
//
//    override val convertToDTO: (PANData0Model) -> PANData0 = panData0Mapper::map
//
//    override val convertToModel: (PANData0) -> PANData0Model = panData0Mapper::map
//
//    override suspend fun createPANData(month: String, year: String, number: String): PANData0Model {
//        return PANData0Model(
//            pan = generateNewNumber(),
//            cardExpiry = createDate(month = month, year = year),
//            cardSecret = generateNewNumber(data = number),
//            exNonce = generateNewNumber()
//        )
//    }
//
//    private suspend fun createDate(month: String, year: String): LocalDate {
//        return LocalDate.of(year.toInt(), month.toInt(), 1)
//    }
//}