package com.example.coinmarket.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.coinmarket.model.dataClass.CoinMarketResponse

@Entity
data class CoinEntity(
val ath: Double,
val atl: Double,
val auditInfoList: List<CoinMarketResponse.Data.CryptoCurrency.AuditInfo>,
val badges: List<Int>,
val circulatingSupply: Double,
val cmcRank: Int,
val dateAdded: String,
val high24h: Double,

@PrimaryKey
val id: Int,

val isActive: Int,
val isAudited: Boolean,
val lastUpdated: String,
val low24h: Double,
val marketPairCount: Int,
val maxSupply: Double,
val name: String,
val quotes: List<CoinMarketResponse.Data.CryptoCurrency.Quote>,
val selfReportedCirculatingSupply: Double,
val slug: String,
val symbol: String,
val totalSupply: Double
)

