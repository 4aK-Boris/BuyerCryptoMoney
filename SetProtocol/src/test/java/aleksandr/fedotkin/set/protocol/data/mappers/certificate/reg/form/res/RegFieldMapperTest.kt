package aleksandr.fedotkin.set.protocol.data.mappers.certificate.reg.form.res

import aleksandr.fedotkin.set.protocol.core.mapper.BaseObjectMapperTest
import aleksandr.fedotkin.set.protocol.data.dto.certificate.reg.form.res.RegField
import aleksandr.fedotkin.set.protocol.domain.models.certificate.reg.form.res.RegFieldModel
import org.koin.test.inject
import kotlin.random.Random

class RegFieldMapperTest : BaseObjectMapperTest<RegFieldModel, RegField>() {

    override val mapper by inject<RegFieldMapper>()

    override suspend fun generateModel(): RegFieldModel {
        return RegFieldModel(
            fieldId = generateNewNumber(),
            fieldName = listOf("dwadwa", "284m8924c23"),
            fieldDesc = "7x1203/2v4 /23",
            fieldLen = Random.nextInt(),
            fieldRequired = true,
            fieldInvisible = false
        )
    }
}