package com.example.coinmarket.ui.feature

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.MyScreens
import com.example.coinmarket.util.chartUrl
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(viewModel: MainViewModel, navController: NavHostController) {

    val getCoinList = remember { mutableStateOf(EmptyCoinList) }
    val filterList = arrayListOf<CryptoCurrencyEntity>()

    LaunchedEffect(Unit) {
        while (true) {
            getCoinList.value = viewModel.getCryptoList.value
            Log.v("testDataFromVar", getCoinList.toString())
            delay(2500)
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {

        SearchToolbar { navController.popBackStack() }

        SearchBox(
            edtValue = viewModel.search.value,
            icon = Icons.Default.Search,
            hint = stringResource(R.string.hint_search_box)
        ) {
            viewModel.search.value = it
        }


        getCoinList.value.forEach {
            if (it.name.lowercase().contains(viewModel.search.value) || it.symbol.lowercase()
                    .contains(viewModel.search.value)
            ) {
                filterList.add(it)
            }
        }


        CryptoList(
            if (viewModel.search.value == "") {
                getCoinList.value
            } else {
                filterList
            }
        ) {
            navController.navigate(MyScreens.DetailScreen.route + "/" + it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchToolbar(onBackPress: () -> Unit) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.search),
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
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
        }
    )

}


@Composable
fun SearchBox(edtValue: String, icon: ImageVector, hint: String, onValueChanges: (String) -> Unit) {
    OutlinedTextField(
        label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        shape = ShapeDefaults.Medium,
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },

        )

}


//crypto list and item
@Composable
fun CryptoList(
    getCoinList: List<CryptoCurrencyEntity>,
    onClickedItem: (Int) -> Unit
) {

    if (getCoinList.size >= 1) {

        LazyColumn(

            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(getCoinList.size) {
                CryptoListItem(getCoinList[it], onClickedItem)
            }
        }

    } else {

        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Loading()
            Text(text = stringResource(R.string.no_crypto))
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
                text = if (coin.name.length > 7) coin.name.subSequence(0, 7)
                    .toString() else coin.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
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
                    text = if (coin.quotes[0].percentChange24h.toString().length > 4)
                        "%" + coin.quotes[0].percentChange24h.toString().subSequence(0, 4)
                    else
                        "%" + coin.quotes[0].percentChange24h.toString()
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
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colorScheme.onBackground
            )



            Text(
                text = if (coin.ath.toString().length > 7)
                    coin.ath.toString().subSequence(0, 7).toString() + " " + coin.symbol
                else
                    coin.ath.toString() + " " + coin.symbol,
                style = TextStyle(
                    fontSize = 14.sp,
                ),
                color = MaterialTheme.colorScheme.onBackground
            )


        }

    }

}

//loading animation
@Composable
fun Loading() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.loading_anime)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(150.dp),
    )
}
