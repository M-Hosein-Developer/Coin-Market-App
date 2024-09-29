package com.example.coinmarket.util

import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import ir.androidcoder.local.dataClass.PriceResponse

const val BASE_URL = "https://api.coinmarketcap.com/"
const val BASE_URL_PRICE = "https://raw.githubusercontent.com/"

val EmptyCoinList = listOf(

    ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency(
        100000.00000000001 , 100000.00000000001 , listOf(-1 , -1) , 10000.0000000001 , -1 , "null" ,10.00000000000000000001,
        0 , -1 , false , "null" , 10.00000000000001 , -1 , 10.00000000000001 ,
        "null" ,
        listOf(
            ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency.Quote(1000000.0009000001 , 1000000.0000000001 , "null" , 1000000.000000001 ,
            1000000.0000000001 , "null" , 1000000.000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.000000001,
            1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 ,
            1000000.0000000001 , 1000000.0000000001, 1000000.0000000001 , 1000000.0000000001)) ,
        1000000.0000000001 ,"null" , "null" , 1000000.0000000001
    )
)

val EmptyCoinListBook = listOf(

    ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency(
        100000.00000000001 , 100000.00000000001 , listOf(-1 , -1) , 10000.0000000001 , -1 , "null" ,10.00000000000000000001,
        0 , -1 , false , "null" , 10.00000000000001 , -1 , 10.00000000000001 ,
        "null" ,
        listOf(
            ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency.Quote(1000000.0009000001 , 1000000.0000000001 , "null" , 1000000.000000001 ,
            1000000.0000000001 , "null" , 1000000.000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.000000001,
            1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 ,
            1000000.0000000001 , 1000000.0000000001, 1000000.0000000001 , 1000000.0000000001)) ,
        1000000.0000000001 ,"null" , "null" , 1000000.0000000001
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