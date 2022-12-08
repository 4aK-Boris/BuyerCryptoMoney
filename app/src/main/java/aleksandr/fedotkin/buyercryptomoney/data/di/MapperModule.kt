package aleksandr.fedotkin.buyercryptomoney.data.di

import aleksandr.fedotkin.buyercryptomoney.data.mappers.CardMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.ProductMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.PurchaseMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.SellerMapper
import aleksandr.fedotkin.buyercryptomoney.data.mappers.BuyMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val mapperModule = module {

    factoryOf(::BuyerMapper)

    factoryOf(::BuyMapper)

    factoryOf(::CardMapper)

    factoryOf(::ProductMapper)

    factoryOf(::PurchaseMapper)

    factoryOf(::SellerMapper)
}
