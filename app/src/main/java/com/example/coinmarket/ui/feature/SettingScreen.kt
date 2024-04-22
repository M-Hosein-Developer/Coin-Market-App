package com.example.coinmarket.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.Gradient2
import com.example.coinmarket.ui.theme.TextBlack

@Composable
fun SettingScreen(navController: NavHostController) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        SettingToolbar {
            navController.popBackStack()
        }

        Language()

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingToolbar(onBackClicked :() -> Unit){

    TopAppBar(
        title = { Text(
            text = "Setting",
            style = TextStyle(
                color = TextBlack,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 8.dp)
        ) },
        navigationIcon = {
            IconButton(onClick = { onBackClicked.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        }
    )


}

@Composable
fun Language() {

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(18.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            painter = painterResource(R.drawable.baseline_language_24),
            contentDescription = null,
            modifier = Modifier.size(25.dp).wrapContentWidth(),
        )

        Text(
            text = "Language",
            modifier = Modifier.padding(start = 12.dp).fillMaxWidth(0.7f),
            style = TextStyle(
                fontSize = 18.sp
            )
        )

        Text(
            text = "Language",
            modifier = Modifier.wrapContentWidth(),
            style = TextStyle(
                fontSize = 18.sp
            ),
            color = Gradient2
        )

    }

}