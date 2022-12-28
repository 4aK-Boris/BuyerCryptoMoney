package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.RequestType
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegFormResTBS
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFormResTBSModel
import org.koin.test.inject

class RegFormResTBSMapperTest : BaseObjectMapperTest<RegFormResTBSModel, RegFormResTBS>() {

    override val mapper by inject<RegFormResTBSMapper>()

    override suspend fun generateModel(): RegFormResTBSModel {
        return RegFormResTBSModel(
            rrpID = generateNewNumber(),
            lidEE = generateNewNumber(),
            challEE2 = generateNewNumber(),
            lidCA = generateNewNumber(),
            challCA = generateNewNumber(),
            caeThumb = generateByteArray(size = 256),
            requestType = RequestType.CIPHER,
            regFormOrReferral = regFormOrReferralMapperTest.generateModel(),
            brandCRLIdentifier = emptyList(),
            thumbs = listOf(generateByteArray(size = 256), generateByteArray(size = 256))
        )
    }

    private val regFormOrReferralMapperTest by inject<RegFormOrReferralMapperTest>()
}