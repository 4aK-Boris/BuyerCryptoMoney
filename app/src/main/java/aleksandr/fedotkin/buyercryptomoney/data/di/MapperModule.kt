package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.CardMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.ProductMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.PurchaseMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SellerMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        BuyerMapper()
    }

    factory {
        BuyMapper(purchaseMapper = get(), cardMapper = get())
    }

    factory {
        CardMapper()
    }

    factory {
        ProductMapper()
    }

    factory {
        PurchaseMapper()
    }

    factory {
        SellerMapper()
    }
}
