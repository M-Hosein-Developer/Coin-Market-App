package com.example.coinmarket.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coinmarket.ui.style.Style

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainToolbar(text : String , onBackPress :() -> Unit){

    TopAppBar(
        title = { Text(
            text = text,
            style = Style.veryLargeBoldTextStyle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) },
        navigationIcon = {
            IconButton(onClick = { onBackPress.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }
    )
}
