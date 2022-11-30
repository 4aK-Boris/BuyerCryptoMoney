package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.mappers.BankCardMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.ProductMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.PurchaseMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SellerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SnippetMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        BankCardMapper()
    }

    factory {
        ProductMapper()
    }

    factory {
        PurchaseMapper(
            bankCardMapper = get(),
            productMapper = get()
        )
    }

    factory {
        SellerMapper()
    }

    factory {
        BuyerMapper()
    }

    factory {
        SnippetMapper()
    }
}