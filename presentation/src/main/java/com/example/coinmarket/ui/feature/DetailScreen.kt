package com.example.coinmarket.ui.feature

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinmarket.R
import ir.androidcoder.local.dataClass.BookmarkResponse
import ir.androidcoder.local.dataClass.CoinMarketResponse
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.GreenShadow
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.ui.theme.RedShadow
import com.example.coinmarket.util.EmptyCoin
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.util.percentToPrice
import com.example.coinmarket.viewModel.MainViewModel
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition

@Composable
fun DetailScreen(viewModel: MainViewModel, coinID: Int, navController: NavHostController) {

    //get data
    val context = LocalContext.current

    val getCoinList = remember { mutableStateOf(EmptyCoin) }
    val getCoinBookmarkList by remember { mutableStateOf(viewModel.getCryptoBookmarkList) }

    viewModel.getCryptoById(coinID) { getCoinList.value = it }



    //screen
    if (getCoinList.value.lastUpdated.length > 6) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {

            DetailToolbar(
                getCoinList.value,
                getCoinBookmarkList.value,
                { navController.navigate(MyScreens.CalculatorScreen.route + "/" + it) },
                { navController.popBackStack() },
                { viewModel.insertBookmark(it) },
                { viewModel.deleteBookmark(it.id) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            CoinItem(getCoinList.value)
            Spacer(modifier = Modifier.height(32.dp))
            Chart(getCoinList.value)
            DataToShow(getCoinList.value){
                context.startActivity(Intent(Intent.ACTION_VIEW , Uri.parse("https://coinmarketcap.com/currencies/$it/")))
            }
        }
    }
}

//tool bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolbar(data: ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency, bookmarkData: List<ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency>, onCalculatorClick :(Float) -> Unit, onBackPress :() -> Unit, onAddBookmarkClick :(ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency) -> Unit, onDeleteBookmarkClicked: (ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency) -> Unit) {

    val bookmarkBtn = remember { mutableStateOf(false) }
    var bookmarkBtnDb = false

    TopAppBar(
        title = {
            Text(
                text = data.name,
                maxLines = 1,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPress.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        },
        actions = {
            IconButton(
                onClick = {
                onCalculatorClick.invoke(data.quotes[0].price.toFloat())
            }
            ) {
                Icon(
                    painter = painterResource(R.drawable.calculator),
                    contentDescription = null,
                    modifier = Modifier.padding(4.dp , end = 4.dp)
                )
            }

            IconButton(
                onClick = {

                    bookmarkBtn.value = !bookmarkBtn.value

                    if (bookmarkBtn.value) {

                        onAddBookmarkClick.invoke(
                            ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency(
                                data.ath,
                                data.atl,
                                data.badges,
                                data.circulatingSupply,
                                data.cmcRank,
                                data.dateAdded,
                                data.high24h,
                                data.id,
                                data.isActive,
                                data.isAudited,
                                data.lastUpdated,
                                data.low24h,
                                data.marketPairCount,
                                data.maxSupply,
                                data.name,
                                listOf(
                                    ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency.Quote(
                                    data.quotes[0].dominance  ,
                                    data.quotes[0].fullyDilluttedMarketCap  ,
                                    data.quotes[0].lastUpdated  ,
                                    data.quotes[0].marketCap  ,
                                    data.quotes[0].marketCapByTotalSupply  ,
                                    data.quotes[0].name  ,
                                    data.quotes[0].percentChange1h  ,
                                    data.quotes[0]. percentChange1y ,
                                    data.quotes[0].percentChange24h  ,
                                    data.quotes[0].percentChange30d  ,
                                    data.quotes[0].percentChange60d  ,
                                    data.quotes[0].percentChange7d  ,
                                    data.quotes[0].percentChange90d  ,
                                    data.quotes[0].price  ,
                                    data.quotes[0].selfReportedMarketCap  ,
                                    data.quotes[0].turnover ,
                                    data.quotes[0].tvl  ,
                                    data.quotes[0].volume24h  ,
                                    data.quotes[0].volume30d  ,
                                    data.quotes[0].volume7d  ,
                                    data.quotes[0].ytdPriceChangePercentage
                                )),
                                data.selfReportedCirculatingSupply,
                                data.slug,
                                data.symbol,
                                data.totalSupply
                            )
                        )

                    }else {

                        onDeleteBookmarkClicked.invoke(
                            ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency(
                                data.ath,
                                data.atl,
                                data.badges,
                                data.circulatingSupply,
                                data.cmcRank,
                                data.dateAdded,
                                data.high24h,
                                data.id,
                                data.isActive,
                                data.isAudited,
                                data.lastUpdated,
                                data.low24h,
                                data.marketPairCount,
                                data.maxSupply,
                                data.name,
                                listOf(
                                    ir.androidcoder.local.dataClass.BookmarkResponse.Data.CryptoCurrency.Quote(
                                    data.quotes[0].dominance  ,
                                    data.quotes[0].fullyDilluttedMarketCap  ,
                                    data.quotes[0].lastUpdated  ,
                                    data.quotes[0].marketCap  ,
                                    data.quotes[0].marketCapByTotalSupply  ,
                                    data.quotes[0].name  ,
                                    data.quotes[0].percentChange1h  ,
                                    data.quotes[0]. percentChange1y ,
                                    data.quotes[0].percentChange24h  ,
                                    data.quotes[0].percentChange30d  ,
                                    data.quotes[0].percentChange60d  ,
                                    data.quotes[0].percentChange7d  ,
                                    data.quotes[0].percentChange90d  ,
                                    data.quotes[0].price  ,
                                    data.quotes[0].selfReportedMarketCap  ,
                                    data.quotes[0].turnover ,
                                    data.quotes[0].tvl  ,
                                    data.quotes[0].volume24h  ,
                                    data.quotes[0].volume30d  ,
                                    data.quotes[0].volume7d  ,
                                    data.quotes[0].ytdPriceChangePercentage
                                )),
                                data.selfReportedCirculatingSupply,
                                data.slug,
                                data.symbol,
                                data.totalSupply
                            )
                        )

                    }


                }
            ) {

                bookmarkData.forEach {
                    if (data.id == it.id) {
                        bookmarkBtnDb = true
                    }
                }

                if (bookmarkBtn.value)
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_24),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                else if (bookmarkBtnDb)
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_24),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                else
                    Icon(
                        painter = painterResource(R.drawable.outline_bookmark_border_24),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )


            }
        }
    )


}

@Composable
fun CoinItem(data: ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl(data.id))
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(55.dp)
                .background(
                    if (data.quotes[0].percentChange24h > 0) {
                        GreenShadow
                    } else {
                        RedShadow
                    }
                )
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                Modifier.padding(start = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = data.symbol,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                if (data.quotes[0].percentChange24h > 0) {
                    Text(
                        text = "%+" + data.quotes[0].percentChange24h.toString().subSequence(0, 4),
                        color = Green
                    )
                } else if (data.quotes[0].percentChange24h < 0) {
                    Text(
                        text = "%" + data.quotes[0].percentChange24h.toString().subSequence(0, 4),
                        color = Red
                    )
                } else {
                    Text(
                        text = "%" + data.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    )
                }

            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = 8.dp)
            ) {

                Text(
                    text = "$" + (data.quotes[0].price.toString()).subSequence(0, 7),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = data.ath.toString().subSequence(0, 7).toString() + " " + data.symbol,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )


            }

        }
    }
}

@Composable
fun Chart(data: ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency) {

    val checkData = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange90d).toFloat()
    val graphData = listOf(
        LineData(
            x = "", y =
            if (checkData <= 1) {
                1
            } else {
                checkData
            }
        ),

        LineData(
            x = "90day",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange90d)
        ),
        LineData(
            x = "60day",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange60d)
        ),
        LineData(
            x = "30day",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange30d)
        ),
        LineData(
            x = "7day",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange7d)
        ),
        LineData(
            x = "1day",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange24h)
        ),
        LineData(
            x = "1hour",
            y = percentToPrice(data.quotes[0].price, data.quotes[0].percentChange1h)
        )
    )

    LineGraph(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 22.dp)
            .padding(top = 14.dp),
        data = graphData,
        style =
        LineGraphStyle(
            visibility = LineGraphVisibility(
                isYAxisLabelVisible = true,
                isCrossHairVisible = true,
                isGridVisible = true,
            ),
            yAxisLabelPosition = LabelPosition.LEFT,
            colors = LineGraphColors(
                lineColor =
                if (data.quotes[0].percentChange24h > 0) {
                    GreenShadow
                } else {
                    RedShadow
                },

                pointColor =
                if (data.quotes[0].percentChange24h > 0) {
                    GreenShadow
                } else {
                    RedShadow
                },

                clickHighlightColor =
                if (data.quotes[0].percentChange24h > 0) {
                    GreenShadow
                } else {
                    RedShadow
                },

                fillType = LineGraphFillType.Gradient(
                    brush = Brush.verticalGradient(
                        listOf(
                            if (data.quotes[0].percentChange24h > 0) {
                                GreenShadow
                            } else {
                                RedShadow
                            },
                            Color.White
                        )
                    )
                )
            )
        )
    )
}

@Composable
fun DataToShow(data : ir.androidcoder.local.dataClass.CoinMarketResponse.Data.CryptoCurrency, onMoreClicked: (String) -> Unit) {


    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .padding(top = 12.dp)
    ) {

        Column {

            Text(
                text = "Volume 24H:  " + (data.quotes[0].volume24h).toString().subSequence(0 , 15),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Volume 7d:  " + (data.quotes[0].volume7d).toString().subSequence(0 , 15),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Volume 30d:  " + (data.quotes[0].volume30d).toString().subSequence(0 , 15),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ){
            Text(
                text = "1H:  " + (data.quotes[0].percentChange1h).toString().subSequence(0 , 5) + "%",
                modifier = Modifier.padding(bottom = 8.dp),
                color =  if (data.quotes[0].percentChange24h > 0) {
                    Green
                } else {
                    Red
                }
            )

            Text(
                text = "1d:  " + (data.quotes[0].percentChange24h).toString().subSequence(0 , 5) + "%",
                modifier = Modifier.padding(bottom = 8.dp),
                color =  if (data.quotes[0].percentChange24h > 0) {
                    Green
                } else {
                    Red
                }
            )

            Text(
                    text = "7d:  " + (data.quotes[0].percentChange7d).toString().subSequence(0 , 5) + "%",
            modifier = Modifier.padding(bottom = 8.dp),
            color =  if (data.quotes[0].percentChange24h > 0) {
                Green
            } else {
                Red
            }
            )
        }

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ){
            Text(
                text = "1M:  " + (data.quotes[0].percentChange30d).toString().subSequence(0 , 5) + "%",
                modifier = Modifier.padding(bottom = 8.dp),
                color =  if (data.quotes[0].percentChange24h > 0) {
                    Green
                } else {
                    Red
                }
            )

            Text(
                text = "60d:  " + (data.quotes[0].percentChange60d).toString().subSequence(0 , 5) + "%",
                modifier = Modifier.padding(bottom = 8.dp),
                color =  if (data.quotes[0].percentChange24h > 0) {
                    Green
                } else {
                    Red
                }
            )

            Text(
                text = "90d:  " + (data.quotes[0].percentChange90d).toString().subSequence(0 , 5) + "%",
                modifier = Modifier.padding(bottom = 8.dp),
                color =  if (data.quotes[0].percentChange24h > 0) {
                    Green
                } else {
                    Red
                }
            )
        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        Column {

            Text(
                text = "Total Supply:  " + data.totalSupply,
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Max Supply:  " + data.maxSupply,
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "High24h:  " + (data.high24h).toString().subSequence(0 , 8),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Low24h:  " + (data.low24h).toString().subSequence(0 , 8),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

        }

        HorizontalDivider(Modifier.padding(vertical = 8.dp))

        Column {


            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ){
                Text(
                    text = "Last Updated:  " + (data.lastUpdated).subSequence(0 , 10) + "%",
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Time:  " + (data.lastUpdated).subSequence(11 , 16) + "%",
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

            }


            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Last Updated:  " + (data.dateAdded).subSequence(0 , 10) + "%",
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Time:  " + (data.dateAdded).subSequence(11 , 16) + "%",
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 8.dp)
    ){

        TextButton(
            onClick = { onMoreClicked.invoke(data.name) },
            Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "More Detail...",
                color = Red,
                style = TextStyle(
                    fontSize = 18.sp
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }
}