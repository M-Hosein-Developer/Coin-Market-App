package com.example.coinmarket.ui.feature

import android.app.ActionBar.LayoutParams
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController

@Composable
fun MoreDetailScreen(navController: NavHostController, id: String) {


    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        val url = "https://coinmarketcap.com/currencies/$id/"

        AndroidView(factory = { context ->
            WebView(context).apply {
                LayoutParams.MATCH_PARENT
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }

}