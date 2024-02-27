@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.coinmarket.ui.feature

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.coinmarket.R
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.ui.theme.White
import com.example.coinmarket.util.EmptyCoin
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(viewModel: MainViewModel, navController: NavHostController) {
    var getCoinList = remember { mutableStateOf(EmptyCoin) }

    LaunchedEffect(Unit) {
        while (true) {
            getCoinList.value = viewModel.getCryptoList.value
            Log.v("testDataFromVar", getCoinList.toString())
            delay(2500)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        HomeToolbar()
        CardSlider(getCoinList.value)
    }

}


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

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        AsyncImage(
            model = R.drawable.slider_card,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                Modifier.padding(top = 48.dp)
            ) {

                Text(
                    text = "$" + (getCoinList[0].quotes[0].price.toString()).subSequence(0 , 10),
                    Modifier.padding(start = 36.dp, bottom = 8.dp),
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = White
                )

                Text(
                    text = "$" + (getCoinList[0].quotes[0].volume24h.toString()).subSequence(0 , 10) + "  vol",
                    Modifier.padding(start = 36.dp, bottom = 8.dp),
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                    ),
                    color = White
                )

                Text(
                    text = "%" + getCoinList[0].quotes[0].percentChange24h ,
                    Modifier.padding(start = 36.dp, bottom = 8.dp),
                    style = TextStyle(
                        color = TextBlack,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                    ),
                    color = White
                )
            }

            AsyncImage(
                model = imageUrl(1),
                contentDescription = null,
                modifier = Modifier.padding(end = 42.dp).size(50.dp),
            )

        }


    }


}