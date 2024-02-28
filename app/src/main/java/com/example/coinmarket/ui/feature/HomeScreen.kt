package com.example.coinmarket.ui.feature

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.coinmarket.R
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.ui.theme.Gradient1
import com.example.coinmarket.ui.theme.Gradient2
import com.example.coinmarket.ui.theme.Gradient3
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.ui.theme.TextLightGray
import com.example.coinmarket.ui.theme.White
import com.example.coinmarket.util.EmptyCoin
import com.example.coinmarket.util.chartUrl
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {

    val getCoinList = remember { mutableStateOf(EmptyCoin) }

    LaunchedEffect(Unit) {
        while (true) {
            getCoinList.value = viewModel.getCryptoList.value
            Log.v("testDataFromVar", getCoinList.toString())
            delay(2500)
        }
    }


    Column(
        modifier = Modifier
            .background(White)
    ) {

        HomeToolbar()
        CardSlider(getCoinList.value)
        CoinList(getCoinList.value)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbar() {

    TopAppBar(
        title = {
            Text(
                text = "Home",
                style = TextStyle(
                    color = TextBlack,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )

}

@Composable
fun CardSlider(getCoinList: List<CoinMarketResponse.Data.CryptoCurrency>) {

    val configuration = LocalConfiguration.current
    val fraction = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 0.9f else 0.3f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
            .padding(horizontal = 16.dp)
            .padding(bottom = 14.dp, top = 18.dp)
            .shadow(12.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {


        Box(
            Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Gradient1,
                            Gradient2,
                            Gradient3
                        )
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (getCoinList.size > 3){

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(bottom = 18.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Text(
                            text = "$" + (getCoinList[0].quotes[0].price.toString()).subSequence(0, 10),
                            Modifier.padding(start = 24.dp, bottom = 8.dp),
                            style = TextStyle(
                                color = TextBlack,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = White
                        )

                        Text(
                            text = "$" + (getCoinList[0].quotes[0].volume24h.toString()).subSequence(
                                0,
                                10
                            ) + "  vol",
                            Modifier.padding(start = 24.dp, bottom = 8.dp),
                            style = TextStyle(
                                color = TextBlack,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                            ),
                            color = White
                        )

                        if (getCoinList[0].quotes[0].percentChange24h > 0) {
                            Text(
                                text = "%+" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = Green
                            )
                        } else if (getCoinList[0].quotes[0].percentChange24h < 0) {
                            Text(
                                text = "%" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = Red
                            )
                        } else {
                            Text(
                                text = "%" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = TextStyle(
                                    color = TextBlack,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                color = TextLightGray
                            )
                        }

                    }

                    AsyncImage(
                        model = imageUrl(1),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 16.dp, end = 24.dp)
                            .size(50.dp),
                    )
                }

            }else{
                Loader()
            }




        }

    }


}

@Composable
fun CoinList(getCoinList: List<CoinMarketResponse.Data.CryptoCurrency>) {

    Row(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Charts",
            color = TextLightGray,
            style = TextStyle(
                fontSize = 18.sp,
            )
        )

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "See All",
                color = Red,
                style = TextStyle(
                    fontSize = 18.sp,
                )
            )
        }

    }

    Column(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (getCoinList.size >= 10) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(10) {
                    CoinListItem(getCoinList[it])
                }
            }
        } else {
            Loader()
        }

    }
}

@Composable
fun CoinListItem(coin: CoinMarketResponse.Data.CryptoCurrency) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl(coin.id))
                .decoderFactory(SvgDecoder.Factory())
                .build(), contentDescription = null,
            modifier = Modifier.size(50.dp)
        )

        Column(
            Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = coin.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                color = TextBlack
            )

            if (coin.quotes[0].percentChange24h > 0) {
                Text(
                    text = "%+" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    color = Green
                )
            } else if (coin.quotes[0].percentChange24h < 0) {
                Text(
                    text = "%" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    color = Red
                )
            } else {
                Text(
                    text = "%" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    color = TextLightGray
                )
            }

        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(chartUrl(coin.id))
                .decoderFactory(SvgDecoder.Factory())
                .build(), contentDescription = null,
            modifier = Modifier.size(120.dp),
            colorFilter =
            if (coin.quotes[0].percentChange24h > 0){
                ColorFilter.tint(Green)
            }else{
                ColorFilter.tint(Red)
            }
        )

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "$" + (coin.quotes[0].price.toString()).subSequence(0, 7),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                color = TextBlack
            )

            Text(
                text = coin.ath.toString().subSequence(0, 7).toString() + " " + coin.symbol,
                style = TextStyle(
                    fontSize = 14.sp,
                ),
                color = TextLightGray
            )


        }

    }

}

@Composable
fun Loader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_anime)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(150.dp),
    )
}