package com.example.coinmarket.ui.feature

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.coinmarket.R
import com.example.coinmarket.model.dataClass.PriceResponse
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

    Log.v("testPrice", dollarPrice.toString())


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        CalculatorToolbar()
        CryptoNumber(coinPrice , dollarPrice)
        CryptoCalculator(coinPrice , dollarPrice)

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
fun CryptoNumber(coinPrice: Float, dollarPrice: PriceResponse) {

    var counter by remember { mutableIntStateOf(1) }

    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 18.dp)
                .shadow(4.dp)
                .padding(8.dp)
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

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 18.dp)
                .shadow(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                contentAlignment = Alignment.CenterStart
            ) {

                AsyncImage(
                    model = R.drawable.irflag,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(18.dp),
                    contentScale = ContentScale.Crop

                )


                Text(
                    text = "IR " + ((coinPrice * (dollarPrice.p.filterNot { it == ',' }
                        .toInt())) * counter).toString(),
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(8.dp)
                )


            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 18.dp)
                .shadow(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                contentAlignment = Alignment.CenterStart
            ) {

                AsyncImage(
                    model = R.drawable.usa,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(18.dp),
                    contentScale = ContentScale.Crop
                )


                Text(
                    text = "$1 = " + dollarPrice.p + " Rial",

                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )

            }
        }





    }

}

@Composable
fun CryptoCalculator(coinPrice: Float, dollarPrice: PriceResponse) {

    var text by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(18.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = text,
            Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = { text += "7" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "7",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "8" },
                modifier = Modifier
                    .shadow(4.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "8",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "9" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "9",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }


        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = { text += "4" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "4",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "5" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "5",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "6" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "6",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }


        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = { text += "1" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "1",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "2" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "2",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "3" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "3",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }


        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = { text.length -= 1 },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "<-",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = { text += "0" },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "0",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = {  },
                modifier = Modifier
//                    .clip(RoundedCornerShape(8.dp))
                    .shadow(4.dp)
                    .weight(0.33f)
                    .padding(8.dp)
            ) {
                Text(
                    text = "=",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }


        }

    }

}
