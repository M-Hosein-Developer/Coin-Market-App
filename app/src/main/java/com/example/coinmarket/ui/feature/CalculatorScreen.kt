package com.example.coinmarket.ui.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.ui.theme.TextBlack
import com.example.coinmarket.util.EmptyDollar
import com.example.coinmarket.viewModel.MainViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun CalculatorScreen(
    viewModel: MainViewModel,
    navController1: NavHostController,
    coinPrice : Float
) {

    var dollarPrice by remember { mutableStateOf(EmptyDollar) }

    LaunchedEffect(coinPrice) {

        viewModel.getDollarPrice.collect{
            if (it != null)
            dollarPrice = it
        }

    }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        CalculatorToolbar()
        CryptoNumber(coinPrice)

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorToolbar() {

    TopAppBar(
        title = {
            Text(
                text = "Calculator",
                style = TextStyle(
                    color = TextBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    )

}

@Composable
fun CryptoNumber(coinPrice: Float) {

    var counter by remember { mutableIntStateOf(1) }

    Row(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 24.dp)
            .shadow(4.dp)
            .padding(12.dp)
            .clip(RoundedCornerShape(100.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = "$ " + (coinPrice * counter).toString(),
            style = TextStyle(
                fontSize = 24.sp
            )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    if (counter == 1) {
                        counter = 1
                    } else {
                        counter--
                    }
                },
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = "-",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                )
            }

            Text(
                text = counter.toString(),
                Modifier.padding(horizontal = 12.dp),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

            Button(
                onClick = { counter++ },
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(
                    text = "+",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                )
            }

        }


    }


}