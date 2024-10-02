package com.example.coinmarket.util

import ir.androidcoder.entities.CryptoCurrencyEntity

const val BASE_URL = "https://api.coinmarketcap.com/"
const val BASE_URL_PRICE = "https://raw.githubusercontent.com/"

val EmptyCoinList = listOf(
    CryptoCurrencyEntity(
        ath = 60000.0,
        atl = 5000.0,
        badges = listOf(1, 2),
        circulatingSupply = 19000000.0,
        cmcRank = 1,
        dateAdded = "2020-01-01",
        high24h = 59000.0,
        id = 1,
        isActive = 1,
        isAudited = true,
        lastUpdated = "2024-10-01",
        low24h = 58000.0,
        marketPairCount = 150,
        maxSupply = 21000000.0,
        name = "Bitcoin",
        quotes = listOf(
            CryptoCurrencyEntity.Quote(
                dominance = 60.0,
                fullyDilluttedMarketCap = 1200000000.0,
                lastUpdated = "2024-10-01",
                marketCap = 1100000000.0,
                marketCapByTotalSupply = 1150000000.0,
                name = "Bitcoin",
                percentChange1h = 0.5,
                percentChange1y = 150.0,
                percentChange24h = -1.2,
                percentChange30d = 10.0,
                percentChange60d = 20.0,
                percentChange7d = 5.0,
                percentChange90d = 30.0,
                price = 59000.0,
                selfReportedMarketCap = 1150000000.0,
                turnover = 0.05,
                tvl = 1000000000.0,
                volume24h = 40000000.0,
                volume30d = 1000000000.0,
                volume7d = 200000000.0,
                ytdPriceChangePercentage = 100.0
            )
        ),
        selfReportedCirculatingSupply = 19000000.0,
        slug = "bitcoin",
        symbol = "BTC",
        totalSupply = 21000000.0
    )
)


val EmptyCoinListBook = listOf(

    CryptoCurrencyEntity(
        ath = 60000.0,
        atl = 5000.0,
        badges = listOf(1, 2),
        circulatingSupply = 19000000.0,
        cmcRank = 1,
        dateAdded = "2020-01-01",
        high24h = 59000.0,
        id = 1,
        isActive = 1,
        isAudited = true,
        lastUpdated = "2024-10-01",
        low24h = 58000.0,
        marketPairCount = 150,
        maxSupply = 21000000.0,
        name = "Bitcoin",
        quotes = listOf(
            CryptoCurrencyEntity.Quote(
                dominance = 60.0,
                fullyDilluttedMarketCap = 1200000000.0,
                lastUpdated = "2024-10-01",
                marketCap = 1100000000.0,
                marketCapByTotalSupply = 1150000000.0,
                name = "Bitcoin",
                percentChange1h = 0.5,
                percentChange1y = 150.0,
                percentChange24h = -1.2,
                percentChange30d = 10.0,
                percentChange60d = 20.0,
                percentChange7d = 5.0,
                percentChange90d = 30.0,
                price = 59000.0,
                selfReportedMarketCap = 1150000000.0,
                turnover = 0.05,
                tvl = 1000000000.0,
                volume24h = 40000000.0,
                volume30d = 1000000000.0,
                volume7d = 200000000.0,
                ytdPriceChangePercentage = 100.0
            )
        ),
        selfReportedCirculatingSupply = 19000000.0,
        slug = "bitcoin",
        symbol = "BTC",
        totalSupply = 21000000.0
    )
)

val EmptyCoin = ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency(
    100000.00000000001 , 100000.00000000001 , listOf(-1 , -1) , 10000.0000000001 , -1 , "null" ,10.00000000000000000001,
    13 , -1 , false , "null" , 10.00000000000001 , -1 , 10.00000000000001 ,
    "null" ,
    listOf(
        ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency.Quote(1000000.0009000001 , 1000000.0000000001 , "null" , 1000000.000000001 ,
        1000000.0000000001 , "null" , 1000000.000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.000000001,
        1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 ,
        1000000.0000000001 , 1000000.0000000001, 1000000.0000000001 , 1000000.0000000001)) ,
    1000000.0000000001 ,"null" , "null" , 1000000.0000000001
)

val EmptyDollar = ir.androidcoder.local.dataClass.PriceResponse(
    "",
    -1,
    "",
    "",
    "",
    "10",
    "",
    "",
    "",
    ""
)