package aleksandr.fedotkin.buyercryptomoney.presentation.di

import aleksandr.fedotkin.buyercryptomoney.domain.model.BuyerModel
import org.koin.dsl.module

val mockModule = module {
    factory {
        BuyerModel(
            id = 0,
            imageUrl = "https://sun1.userapi.com/sun1-97/s/v1/ig2/80TOhayqCnWijE6DO_rymIUsR-DNauDbX17-5D6cu3Msvmsk97bo44ZJsUZ7sF-thljTpnu--ezCha1I4lqisim1.jpg?size=629x697&quality=96&type=album",
            nickName = "Тащер",
            amountOfMoney = 1000000
        )
    }
}
