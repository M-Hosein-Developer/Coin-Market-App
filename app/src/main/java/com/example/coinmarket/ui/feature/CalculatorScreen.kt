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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.coinmarket.R
import com.example.coinmarket.model.dataClass.PriceResponse
import com.example.coinmarket.ui.theme.Orange
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
    ) {

        CalculatorToolbar{
            navController1.popBackStack()
        }

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {

            CryptoNumber(coinPrice , dollarPrice)

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 18.dp),
                thickness = 2.dp,
                color = TextBlack
            )

            CryptoCalculator(coinPrice , dollarPrice)

        }




    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorToolbar(onBackCLicked: () -> Unit) {

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
        },
        navigationIcon = {
            IconButton(onClick = { onBackCLicked.invoke() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)
            }
        },
    )

}

@Composable
fun CryptoNumber(coinPrice: Float, dollarPrice: PriceResponse) {

    var counter by remember { mutableIntStateOf(1) }

    Column {

        Row(
            Modifier
                .fillMaxWidth()
                .height(130.dp)
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
                .height(130.dp)
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
                .height(130.dp)
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
            .padding(start = 18.dp, end = 18.dp, bottom = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "$text  Rial",
            Modifier
                .fillMaxWidth()
                .height(62.dp)
                .wrapContentHeight(Alignment.CenterVertically),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            maxLines = 1,

        )

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = { text += "7" },
                modifier = Modifier
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                    .weight(0.33f)
                    .padding(4.dp)
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
                onClick = {
                    if (text.isNotEmpty())
                        text = text.dropLast(1)
                          },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Orange)
                    .weight(0.33f)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically),
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null)
            }

            TextButton(
                onClick = { text += "0" },
                modifier = Modifier
                    .weight(0.33f)
                    .padding(4.dp)
            ) {
                Text(
                    text = "0",
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }

            TextButton(
                onClick = {
                    if (text != "" || text.isNotEmpty())
                        text = (text.toFloat() / (dollarPrice.p.filterNot { it == ',' }.toFloat()) / coinPrice).toString()
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Green)
                    .weight(0.33f)
                    .padding(4.dp)
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
