package com.example.coinmarket.util

import com.example.coinmarket.model.dataClass.CoinMarketResponse

const val BASE_URL = "https://api.coinmarketcap.com/"

val EmptyCoin = listOf(

    CoinMarketResponse.Data.CryptoCurrency(
        100000.00000000001 , 100000.00000000001 , listOf(-1 , -1) , 10000.0000000001 , -1 , "null" ,10.00000000000000000001,
        0 , -1 , false , "null" , 10.00000000000001 , -1 , 10.00000000000001 ,
        "null" ,
        listOf(CoinMarketResponse.Data.CryptoCurrency.Quote(1000000.0009000001 , 1000000.0000000001 , "null" , 1000000.000000001 ,
            1000000.0000000001 , "null" , 1000000.000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.000000001,
            1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 , 1000000.0000000001 ,
            1000000.0000000001 , 1000000.0000000001, 1000000.0000000001 , 1000000.0000000001)) ,
        1000000.0000000001 ,"null" , "null" , 1000000.0000000001
    )
)