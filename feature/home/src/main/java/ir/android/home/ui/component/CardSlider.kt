package ir.android.home.ui.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.android.ui.component.CryptoLoading
import ir.android.ui.style.Style
import ir.android.ui.theme.CoinMarketTheme
import ir.android.ui.theme.Gradient1
import ir.android.ui.theme.Gradient2
import ir.android.ui.theme.Gradient3
import ir.android.ui.util.imageUrl
import ir.androidcoder.Model.CryptoCurrency

@Composable
fun CardSlider(getCoinList: List<CryptoCurrency>) {

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
                CryptoLoading()
            }
        }
    }
}

@Preview
@Composable
private fun CardSliderPreview() {
    CoinMarketTheme {
        CardSlider(emptyList())
    }
}