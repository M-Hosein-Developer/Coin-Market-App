@file:Suppress("DEPRECATION")

package com.example.coinmarket.ui.feature

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
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
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ir.androidcoder.entities.CryptoCurrencyEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(viewModel: MainViewModel, navController: NavHostController) {

    viewModel.getCryptoListFormDatabase()
    val getCoinList = remember { mutableStateOf<LazyPagingItems<CryptoCurrencyEntity>?>(null) }

    LaunchedEffect(Unit) {
        while (true) {

            Log.v("testDataFromVar", getCoinList.toString())
            delay(2500)
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(stringResource(R.string.search)){ navController.popBackStack() }

        SearchBox(
            edtValue = viewModel.search.value,
            icon = Icons.Default.Search,
            hint = stringResource(R.string.hint_search_box)
        ) {
            viewModel.search.value = it
            viewModel.getCryptoListFormDatabase()
        }


        CryptoList(
            viewModel
        ) {
            navController.navigate(MyScreens.DetailScreen.route + "/" + it)
        }
    }
}

@Composable
fun SearchBox(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(hint , style = Style.baseTextStyle) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) }
    )
}


//crypto list and item
@SuppressLint("SuspiciousIndentation")
@Composable
fun CryptoList(
    viewModel: MainViewModel,
    onClickedItem: (Int) -> Unit
) {

    val data = viewModel.getCryptoListFormDatabase.value?.collectAsLazyPagingItems()
    val isRefresh = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        while (true){
            data?.refresh()
            delay(10000)
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefresh.value),
        onRefresh = {
            isRefresh.value = true
            data?.refresh()
            scope.launch {
                delay(2000)
                isRefresh.value = false
            }
        }
    ) {
        LazyColumn(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (data != null) {
                items(data.itemCount) { index ->
                    data[index]?.let { CryptoListItem(it, onClickedItem) }
                }
                data.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> item { Loading() }
                        loadState.append is LoadState.Loading -> item { Loading() }
                    }
                }
            }
        }
    }
}

@Composable
fun CryptoListItem(coin: CryptoCurrencyEntity, onClickedItem: (Int) -> Unit) {

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
                text = if (coin.name.length > 7) coin.name.subSequence(0, 7).toString() else coin.name,
                style = Style.xNormalTextStyle.copy(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.padding(bottom = 8.dp),
            )

            if (coin.quotes[0].percentChange1h > 0) {
                Text(
                    text = "%+" + coin.quotes[0].percentChange1h.toString().subSequence(0, 3),
                    style = Style.greenNormalTextStyle
                )
            } else if (coin.quotes[0].percentChange1h < 0) {
                Text(
                    text = "%" + coin.quotes[0].percentChange1h.toString().subSequence(0, 3),
                    style = Style.redNormalTextStyle
                )
            } else {
                Text(
                    text = if (coin.quotes[0].percentChange1h.toString().length > 4)
                        "%" + coin.quotes[0].percentChange1h.toString().subSequence(0, 4)
                    else
                        "%" + coin.quotes[0].percentChange1h.toString(),
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
            if (coin.quotes[0].percentChange1h > 0) {
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
                text = if (coin.ath.toString().length > 7)
                    coin.ath.toString().subSequence(0, 7).toString() + " " + coin.symbol
                else
                    coin.ath.toString() + " " + coin.symbol,
                style = Style.baseTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }
    }
}

//loading animation
@Composable
fun Loading() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.loading_anime)
        )

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(150.dp),
        )
    }
}
