package com.example.coinmarket.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.example.coinmarket.ui.MainToolbar
import com.example.coinmarket.ui.style.Style
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.util.chartUrl
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity

@Composable
fun BookmarkScreen(navController: NavHostController, viewModel: MainViewModel) {

    val getBookmarkData by remember { mutableStateOf(viewModel.getCryptoBookmarkList.value) }

    Column(
        Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(stringResource(R.string.bookmark)){ navController.popBackStack() }

        CryptoBookmarkList(
            getBookmarkData
        ) {
            navController.navigate(MyScreens.DetailScreen.route + "/" + it)
        }

    }

}

@Composable
fun CryptoBookmarkList(getCoinList: List<CryptoCurrencyEntity>, onClickedItem: (Int) -> Unit) {

    if (getCoinList.size >= 1) {

        LazyColumn(

            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(getCoinList.size) {
                CryptoBookmarkListItem(getCoinList[it], onClickedItem)
            }
        }

    } else {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BookmarkLoading()
            Text(
                text = stringResource(R.string.no_crypto),
                style = Style.baseTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }

    }


}

@Composable
fun CryptoBookmarkListItem(coin: CryptoCurrencyEntity, onClickedItem: (Int) -> Unit) {

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
            modifier = Modifier.size(50.dp)
        )

        Column(
            Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = coin.name,
                style = Style.xNormalTextStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(bottom = 8.dp),
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
                .build(), contentDescription = null,
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
                modifier = Modifier.padding(bottom = 8.dp),
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
fun BookmarkLoading() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_anime)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(150.dp),
    )
}

