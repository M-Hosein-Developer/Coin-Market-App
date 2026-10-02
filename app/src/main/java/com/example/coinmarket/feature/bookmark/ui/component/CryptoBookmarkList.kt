package com.example.coinmarket.feature.bookmark.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coinmarket.R
import com.example.coinmarket.coreUi.component.CryptoLoading
import com.example.coinmarket.ui.style.Style
import com.example.coinmarket.ui.theme.CoinMarketTheme
import ir.androidcoder.Model.CryptoCurrency

@Composable
fun CryptoBookmarkList(coinList: List<CryptoCurrency>, onItemClicked: (Int) -> Unit) {

    if (coinList.isNotEmpty()) {
        LazyColumn(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(coinList.size) {
                CryptoBookmarkItem(coinList[it], onItemClicked)
            }
        }

    } else {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CryptoLoading()
            Text(
                text = stringResource(R.string.no_crypto),
                style = Style.baseTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )
        }

    }
}

@Preview
@Composable
private fun CryptoBookmarkListPreview() {
    CoinMarketTheme {
        CryptoBookmarkList(emptyList()) { }
    }
}