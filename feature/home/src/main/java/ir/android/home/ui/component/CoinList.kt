package ir.android.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.android.ui.R
import ir.android.ui.component.CryptoLoading
import ir.android.ui.style.Style
import ir.android.ui.theme.CoinMarketTheme
import ir.androidcoder.Model.CryptoCurrency

@Composable
fun CoinList(
    coinList: List<CryptoCurrency>,
    onSearchClicked: () -> Unit,
    onItemClicked: (Int) -> Unit
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
            onClick = { onSearchClicked.invoke() }
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

        if (coinList.size >= 10) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(10) { it ->
                    CoinItem(coinList[it]) { onItemClicked.invoke(it) }
                }
            }
        } else {
            CryptoLoading()
        }
    }
}

@Preview
@Composable
private fun CoinListPreview() {
    CoinMarketTheme {
        CoinList(
            emptyList(),
            {}
        ) { }
    }
}