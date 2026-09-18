package com.example.coinmarket.ui.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.coinmarket.R
import com.example.coinmarket.ui.style.Style
import com.example.coinmarket.ui.theme.Gradient1
import com.example.coinmarket.ui.theme.Gradient2
import com.example.coinmarket.ui.theme.Gradient3
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.GreenShadow
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.ui.theme.RedShadow
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.util.chartUrl
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController , onHamburgerClick :() -> Unit) {

    //get data
    val getCoinList = remember { mutableStateOf(EmptyCoinList) }
    LaunchedEffect(Unit) {
        while (true) {
            getCoinList.value = viewModel.getCryptoList.value
            Log.v("testDataFromVar", getCoinList.toString())
            delay(2500)
        }
    }


    //screen
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        HomeToolbar{
            onHamburgerClick.invoke()
        }
        CardSlider(getCoinList.value)
        CoinList(
            getCoinList.value,
            { navController.navigate(MyScreens.SearchScreen.route) },
            { navController.navigate(MyScreens.DetailScreen.route + "/" + it) }
        )
    }

}


//tool bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolbar(onHamburgerClick :() -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.home),
                style = Style.veryLargeBoldTextStyle,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onHamburgerClick.invoke()}
            ) {
                Icon(imageVector = Icons.Outlined.Menu , contentDescription = null)
            }
        }
    )

}

//card
@Composable
fun CardSlider(getCoinList: List<CryptoCurrencyEntity>) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
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
                            Gradient1, Gradient2, Gradient3
                        )
                    )
                )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            if (getCoinList.size > 3) {

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
                            text = "$" + (getCoinList[0].quotes[0].price.toString()).subSequence(
                                0,
                                10
                            ),
                            Modifier.padding(start = 24.dp, bottom = 8.dp),
                            style = Style.whiteVeryLargeTextStyle,
                            color = Color.White
                        )

                        Text(
                            text = "$" + (getCoinList[0].quotes[0].volume24h.toString()).subSequence(
                                0,
                                10
                            ) + "  vol",
                            Modifier.padding(start = 24.dp, bottom = 8.dp),
                            style = Style.largeLightTextStyle,
                            color = Color.White
                        )

                        if (getCoinList[0].quotes[0].percentChange24h > 0) {
                            Text(
                                text = "%+" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = Style.greenXNormalBoldTextStyle
                            )
                        } else if (getCoinList[0].quotes[0].percentChange24h < 0) {
                            Text(
                                text = "%" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = Style.redXNormalBoldTextStyle
                            )
                        } else {
                            Text(
                                text = "%" + (getCoinList[0].quotes[0].percentChange24h).toString()
                                    .subSequence(0, 4),
                                Modifier.padding(start = 24.dp, bottom = 8.dp),
                                style = Style.xNormalTextStyle,
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

            } else {
                Loader()
            }


        }

    }


}

//crypto list and item
@Composable
fun CoinList(
    getCoinList: List<CryptoCurrencyEntity>,
    onClickedSearch: () -> Unit,
    onClickedItem: (Int) -> Unit
) {

    Row(
        Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(R.string.charts),
            style = Style.xNormalTextStyle.copy(MaterialTheme.colorScheme.onBackground)
        )

        TextButton(
            onClick = { onClickedSearch.invoke() }
        ) {
            Text(
                text = stringResource(R.string.see_all),
                style = Style.redXNormalTextStyle
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
                items(10) { it ->
                    CoinListItem(getCoinList[it]) { onClickedItem.invoke(it) }
                }
            }
        } else {
            Loader()
        }

    }
}

@Composable
fun CoinListItem(coin: CryptoCurrencyEntity, onClickedItem: (Int) -> Unit) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickedItem.invoke(coin.id) }
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl(coin.id))
                .decoderFactory(SvgDecoder.Factory())
                .build(), contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(55.dp)
                .background(
                    if (coin.quotes[0].percentChange24h > 0) {
                        GreenShadow
                    } else {
                        RedShadow
                    }
                )
                .padding(4.dp)
                .align(Alignment.CenterVertically)
        )

        Column(
            Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = coin.name,
                style = Style.xNormalTextStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (coin.quotes[0].percentChange24h > 0) {
                Text(
                    text = "%+" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    style = Style.greenNormalTextStyle
                )
            } else if (coin.quotes[0].percentChange24h < 0) {
                Text(
                    text = "%" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    style = Style.redNormalTextStyle
                )
            } else {
                Text(
                    text = "%" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4),
                    style = Style.baseTextStyle
                )
            }

        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(chartUrl(coin.id))
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = Modifier.size(120.dp),
            colorFilter =
            if (coin.quotes[0].percentChange24h > 0) {
                ColorFilter.tint(Green)
            } else {
                ColorFilter.tint(Red)
            }
        )


        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = "$" + (coin.quotes[0].price.toString()).subSequence(0, 7),
                style = Style.xNormalBoldTextStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = coin.ath.toString().subSequence(0, 7).toString() + " " + coin.symbol,
                style = Style.baseTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )


        }

    }

}

//loading animation
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