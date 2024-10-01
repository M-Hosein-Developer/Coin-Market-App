package ir.androidcoder.mapper

import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse

//Crypto Response
//to entity
fun CoinMarketResponse.Data.CryptoCurrency.toCryptoEntity(): CryptoCurrencyEntity = CryptoCurrencyEntity(
        ath = this.ath,
        atl = this.atl,
        badges = this.badges,
        circulatingSupply = this.circulatingSupply,
        cmcRank = this.cmcRank,
        dateAdded = this.dateAdded,
        high24h = this.high24h,
        id = this.id,
        isActive = this.isActive,
        isAudited = this.isAudited,
        lastUpdated = this.lastUpdated,
        low24h = this.low24h,
        marketPairCount = this.marketPairCount,
        maxSupply = this.maxSupply,
        name = this.name,
        quotes = this.quotes.map { it.toCryptoEntity() },  // نگاشت لیست quotes
        selfReportedCirculatingSupply = this.selfReportedCirculatingSupply,
        slug = this.slug,
        symbol = this.symbol,
        totalSupply = this.totalSupply
    )


fun CoinMarketResponse.Data.CryptoCurrency.Quote.toCryptoEntity(): CryptoCurrencyEntity.Quote = CryptoCurrencyEntity.Quote(
        dominance = this.dominance,
        fullyDilluttedMarketCap = this.fullyDilluttedMarketCap,
        lastUpdated = this.lastUpdated,
        marketCap = this.marketCap,
        marketCapByTotalSupply = this.marketCapByTotalSupply,
        name = this.name,
        percentChange1h = this.percentChange1h,
        percentChange1y = this.percentChange1y,
        percentChange24h = this.percentChange24h,
        percentChange30d = this.percentChange30d,
        percentChange60d = this.percentChange60d,
        percentChange7d = this.percentChange7d,
        percentChange90d = this.percentChange90d,
        price = this.price,
        selfReportedMarketCap = this.selfReportedMarketCap,
        turnover = this.turnover,
        tvl = this.tvl,
        volume24h = this.volume24h,
        volume30d = this.volume30d,
        volume7d = this.volume7d,
        ytdPriceChangePercentage = this.ytdPriceChangePercentage
    )


fun CoinMarketResponse.Data.CryptoCurrency.AuditInfo.toEntity(): CryptoCurrencyEntity.AuditInfo = CryptoCurrencyEntity.AuditInfo(
        auditStatus = this.auditStatus,
        auditTime = this.auditTime,
        auditor = this.auditor,
        coinId = this.coinId,
        contractAddress = this.contractAddress,
        contractPlatform = this.contractPlatform,
        reportUrl = this.reportUrl,
        score = this.score
    )


//to model

fun CryptoCurrencyEntity.toCryptoModel(): BookmarkResponse.Data.CryptoCurrency = BookmarkResponse.Data.CryptoCurrency(
        ath = this.ath,
        atl = this.atl,
        badges = this.badges,
        circulatingSupply = this.circulatingSupply,
        cmcRank = this.cmcRank,
        dateAdded = this.dateAdded,
        high24h = this.high24h,
        id = this.id,
        isActive = this.isActive,
        isAudited = this.isAudited,
        lastUpdated = this.lastUpdated,
        low24h = this.low24h,
        marketPairCount = this.marketPairCount,
        maxSupply = this.maxSupply,
        name = this.name,
        quotes = this.quotes.map { it.toModel() },  // Map list of quotes
        selfReportedCirculatingSupply = this.selfReportedCirculatingSupply,
        slug = this.slug,
        symbol = this.symbol,
        totalSupply = this.totalSupply
    )


fun CryptoCurrencyEntity.Quote.toModel(): BookmarkResponse.Data.CryptoCurrency.Quote = BookmarkResponse.Data.CryptoCurrency.Quote(
        dominance = this.dominance,
        fullyDilluttedMarketCap = this.fullyDilluttedMarketCap,
        lastUpdated = this.lastUpdated,
        marketCap = this.marketCap,
        marketCapByTotalSupply = this.marketCapByTotalSupply,
        name = this.name,
        percentChange1h = this.percentChange1h,
        percentChange1y = this.percentChange1y,
        percentChange24h = this.percentChange24h,
        percentChange30d = this.percentChange30d,
        percentChange60d = this.percentChange60d,
        percentChange7d = this.percentChange7d,
        percentChange90d = this.percentChange90d,
        price = this.price,
        selfReportedMarketCap = this.selfReportedMarketCap,
        turnover = this.turnover,
        tvl = this.tvl,
        volume24h = this.volume24h,
        volume30d = this.volume30d,
        volume7d = this.volume7d,
        ytdPriceChangePercentage = this.ytdPriceChangePercentage
    )


fun CryptoCurrencyEntity.AuditInfo.toCryptoModel(): CoinMarketResponse.Data.CryptoCurrency.AuditInfo =
    CoinMarketResponse.Data.CryptoCurrency.AuditInfo(
        auditStatus = this.auditStatus,
        auditTime = this.auditTime,
        auditor = this.auditor,
        coinId = this.coinId,
        contractAddress = this.contractAddress,
        contractPlatform = this.contractPlatform,
        reportUrl = this.reportUrl,
        score = this.score
    )

//--------------------------------------------------------------------------------------------------

fun BookmarkResponse.Data.CryptoCurrency.toBookmarkEntity(): CryptoCurrencyEntity = CryptoCurrencyEntity(
        ath = this.ath,
        atl = this.atl,
        badges = this.badges,
        circulatingSupply = this.circulatingSupply,
        cmcRank = this.cmcRank,
        dateAdded = this.dateAdded,
        high24h = this.high24h,
        id = this.id,
        isActive = this.isActive,
        isAudited = this.isAudited,
        lastUpdated = this.lastUpdated,
        low24h = this.low24h,
        marketPairCount = this.marketPairCount,
        maxSupply = this.maxSupply,
        name = this.name,
        quotes = this.quotes.map { it.toBookmarkEntity() },  // Map list of quotes
        selfReportedCirculatingSupply = this.selfReportedCirculatingSupply,
        slug = this.slug,
        symbol = this.symbol,
        totalSupply = this.totalSupply
)

fun BookmarkResponse.Data.CryptoCurrency.Quote.toBookmarkEntity(): CryptoCurrencyEntity.Quote = CryptoCurrencyEntity.Quote(
        dominance = this.dominance,
        fullyDilluttedMarketCap = this.fullyDilluttedMarketCap,
        lastUpdated = this.lastUpdated,
        marketCap = this.marketCap,
        marketCapByTotalSupply = this.marketCapByTotalSupply,
        name = this.name,
        percentChange1h = this.percentChange1h,
        percentChange1y = this.percentChange1y,
        percentChange24h = this.percentChange24h,
        percentChange30d = this.percentChange30d,
        percentChange60d = this.percentChange60d,
        percentChange7d = this.percentChange7d,
        percentChange90d = this.percentChange90d,
        price = this.price,
        selfReportedMarketCap = this.selfReportedMarketCap,
        turnover = this.turnover,
        tvl = this.tvl,
        volume24h = this.volume24h,
        volume30d = this.volume30d,
        volume7d = this.volume7d,
        ytdPriceChangePercentage = this.ytdPriceChangePercentage
)
