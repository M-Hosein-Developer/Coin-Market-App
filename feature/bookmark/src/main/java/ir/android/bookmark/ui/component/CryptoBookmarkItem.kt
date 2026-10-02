package ir.android.bookmark.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import ir.android.ui.style.Style
import ir.android.ui.theme.CoinMarketTheme
import ir.android.ui.theme.Green
import ir.android.ui.theme.Red
import ir.android.ui.util.EmptyCoin
import ir.android.ui.util.chartUrl
import ir.android.ui.util.imageUrl
import ir.androidcoder.Model.CryptoCurrency

@Composable
fun CryptoBookmarkItem(coin: CryptoCurrency, onClickedItem: (Int) -> Unit) {

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

@Preview
@Composable
private fun CryptoBookmarkItemPreview() {
    CoinMarketTheme {
        CryptoBookmarkItem(EmptyCoin) { }
    }
}