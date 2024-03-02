package com.example.coinmarket.ui.feature

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinmarket.model.dataClass.CoinMarketResponse
import com.example.coinmarket.ui.theme.Green
import com.example.coinmarket.ui.theme.Red
import com.example.coinmarket.ui.theme.RedShadow
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.ui.theme.TextLightGray
import com.example.coinmarket.ui.theme.White
import com.example.coinmarket.util.EmptyCoin
import com.example.coinmarket.util.imageUrl
import com.example.coinmarket.viewModel.MainViewModel

@Composable
fun DetailScreen(viewModel: MainViewModel, coinID: Int) {

    //get data
    val getCoinList = remember { mutableStateOf(EmptyCoin) }

    viewModel.getCryptoById(coinID){
        getCoinList.value = it
    }

    //screen
    Column(
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {

        DetailToolbar(getCoinList.value)
        Spacer(modifier = Modifier.height(16.dp))
        CoinItem(getCoinList.value)
    }

}

//tool bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolbar(data: CoinMarketResponse.Data.CryptoCurrency) {

    TopAppBar(
        title = {
            Text(
                text = data.name,
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
fun CoinItem(data : CoinMarketResponse.Data.CryptoCurrency) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
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
                .background(RedShadow)
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
                    color = TextBlack
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
                        color = TextLightGray
                    )
                }

            }

            Column(
                horizontalAlignment = Alignment.End
            ) {

                Text(
                    text = "$" + (data.quotes[0].price.toString()).subSequence(0, 7),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = TextBlack
                )

                Text(
                    text = data.ath.toString().subSequence(0, 7).toString() + " " + data.symbol,
                    style = TextStyle(
                        fontSize = 14.sp,
                    ),
                    color = TextLightGray
                )


            }

        }
    }
}