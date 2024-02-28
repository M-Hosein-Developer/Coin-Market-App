package com.example.coinmarket.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.v("error" , "Error -> " + throwable.message)
}

fun imageUrl(id: Int) : String{
    return "https://s2.coinmarketcap.com/static/img/coins/64x64/$id.png"
}

fun chartUrl(id: Int) : String{
    return "https://s3.coinmarketcap.com/generated/sparklines/web/7d/2781/$id.svg"
}