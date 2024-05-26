package com.example.coinmarket.ui.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.theme.introTextColor
import com.example.coinmarket.util.MyScreens

@Composable
fun IntroScreen(navController: NavHostController) {

    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart
    ) {

        Image(
            painter = painterResource(R.drawable.intro2),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()
        ) {

            Row(
                Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Coin Market ", style = TextStyle(
                        fontSize = 42.sp, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(start = 32.dp, top = 42.dp), color = Color.White
                )

                Text(
                    text = "Cap", color = introTextColor, style = TextStyle(
                        fontSize = 42.sp, fontWeight = FontWeight.Bold
                    ), modifier = Modifier.padding(top = 42.dp)
                )

            }

            Column {


                Text(
                    text = "Monitor, buy and profit from cryptocurrencies !!", style = TextStyle(
                        fontSize = 36.sp, fontWeight = FontWeight.Bold
                    ), modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(32.dp), color = Color.White
                )

                Button(
                    onClick = {
                        navController.navigate(MyScreens.SignInScreen.route)
                    },
                    modifier = Modifier
                        .padding(start = 32.dp, bottom = 42.dp)
                        .fillMaxWidth(0.35f),
                    colors = ButtonDefaults.buttonColors(introTextColor)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_forward_ios_24),
                        contentDescription = null
                    )

                }


            }

        }


    }

}

