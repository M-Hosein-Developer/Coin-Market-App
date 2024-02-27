package com.example.coinmarket.model.remote

import com.example.coinmarket.model.dataClass.CoinMarketResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data-api/v3/cryptocurrency/listing")
    suspend fun getCryptoList(
        @Query("start") start: Int = 1,
        @Query("limit") limit: Int = 1000,
        @Query("sortBy") sortBy: String = "market_cap",
        @Query("sortType") sortType: String = "desc",
        @Query("convert") convert: String = "USD",
        @Query("cryptoType") cryptoType: String = "all",
        @Query("tagType") tagType: String = "all",
        @Query("audited") audited: Boolean = false,
        @Query("aux") aux: String = "ath,atl,high24h,low24h,num_market_pairs,cmc_rank,date_added,max_supply,circulating_supply,total_supply,volume_7d,volume_30d,self_reported_circulating_supply,self_reported_market_cap"
    ): CoinMarketResponse

}