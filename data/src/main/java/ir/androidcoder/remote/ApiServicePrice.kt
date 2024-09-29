package ir.androidcoder.remote

import ir.androidcoder.local.dataClass.PriceResponse
import retrofit2.http.GET

interface ApiServicePrice {

    @GET("margani/pricedb/main/tgju/current/price_dollar_rl/latest.json")
    suspend fun getPriceDollar() : PriceResponse

}