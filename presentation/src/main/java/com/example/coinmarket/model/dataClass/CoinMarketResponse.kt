package com.example.coinmarket.model.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

data class CoinMarketResponse(
    val `data`: Data,
    val status: Status
) {
    data class Data(
        val cryptoCurrencyList: List<CryptoCurrency>,
        val totalCount: String
    ) {
        @Entity
        data class CryptoCurrency(
            val ath: Double,
            val atl: Double,
//            val auditInfoList: List<AuditInfo>,
            val badges: List<Int>,
            val circulatingSupply: Double,
            val cmcRank: Int,
            val dateAdded: String,
            val high24h: Double,
            @PrimaryKey(autoGenerate = false)
            val id: Int,
            val isActive: Int,
            val isAudited: Boolean,
            val lastUpdated: String,
            val low24h: Double,
            val marketPairCount: Int,
            val maxSupply: Double,
            val name: String,
            val quotes: List<Quote>,
            val selfReportedCirculatingSupply: Double,
            val slug: String,
            val symbol: String,
            val totalSupply: Double
        ) {
            data class AuditInfo(
                val auditStatus: Int,
                val auditTime: String,
                val auditor: String,
                val coinId: String,
                val contractAddress: String,
                val contractPlatform: String,
                val reportUrl: String,
                val score: String
            )

            data class Quote(
                val dominance: Double,
                val fullyDilluttedMarketCap: Double,
                val lastUpdated: String,
                val marketCap: Double,
                val marketCapByTotalSupply: Double,
                val name: String,
                val percentChange1h: Double,
                val percentChange1y: Double,
                val percentChange24h: Double,
                val percentChange30d: Double,
                val percentChange60d: Double,
                val percentChange7d: Double,
                val percentChange90d: Double,
                val price: Double,
                val selfReportedMarketCap: Double,
                val turnover: Double,
                val tvl: Double,
                val volume24h: Double,
                val volume30d: Double,
                val volume7d: Double,
                val ytdPriceChangePercentage: Double
            )
        }
    }

    data class Status(
        val credit_count: Int,
        val elapsed: String,
        val error_code: String,
        val error_message: String,
        val timestamp: String
    )
}