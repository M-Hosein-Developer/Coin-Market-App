package com.example.coinmarket.ui.feature

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.ui.theme.introTextColor

@Composable
fun InfoScreen(navController: NavHostController) {

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        InfoToolbar { navController.popBackStack() }

        Info(
            {
                val recipientEmail = it // ایمیل شما
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:$recipientEmail")
                intent.putExtra(Intent.EXTRA_SUBJECT, "موضوع ایمیل")
                intent.putExtra(Intent.EXTRA_TEXT, "متن ایمیل")
                context.startActivity(intent)
            },
            {
                val linkedInProfileUrl = "https://www.linkedin.com/in/$it" // لینک حساب کاربری شما
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(linkedInProfileUrl)
                context.startActivity(intent)
            },
            {
                val instagramProfileUrl = "https://www.instagram.com/$it" // لینک حساب کاربری شما
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(instagramProfileUrl)
                context.startActivity(intent)
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoToolbar(onBackPress :() -> Unit){

    TopAppBar(
        title = { Text(
            text = "Info",
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            ),
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

@Composable
fun Info(onEmailClicked: (String) -> Unit , onLinkedInClicked: (String) -> Unit , onInstagramClicked: (String) -> Unit) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Coin Market ",
                    style = TextStyle(
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(start = 32.dp, top = 12.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Cap",
                    color = introTextColor,
                    style = TextStyle(
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 12.dp)
                )

            }

            Column {

                Text(
                    text = "Description: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp , start = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "In this application, I tried to develop an excellent viewer for cryptos that can be used online and you can instantly use its online data. You can bookmark the crypto you want. Search for the crypto you want from among 500 cryptos",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .padding(start = 32.dp , top = 18.dp , end = 32.dp),
                    lineHeight = 32.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 32.dp)
            )

            Row {

                Text(
                    text = "Developer: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp , start = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = " Mohammad Hosein Hajiakbari ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

            } // Dev

            Row {

                Text(
                    text = "Email: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp , start = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = " m.hosein.developer@gmail.com",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable { onEmailClicked.invoke("m.hosein.developer@gmail.com") },
                    color = MaterialTheme.colorScheme.onBackground
                )

            } // Email

            Row {

                Text(
                    text = "LinkedIn: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp , start = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = " Mohammad Hosein Hajiakbari ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable { onLinkedInClicked.invoke("mohammad-hosein-hajiakbari-662337246") },
                    color = MaterialTheme.colorScheme.onBackground
                )

            } // LinkedIn

            Row {

                Text(
                    text = "Instagram: ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp , start = 32.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = " android.coder ",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable { onInstagramClicked.invoke("android.coder") },
                    color = MaterialTheme.colorScheme.onBackground
                )

            } // Instagram

            HorizontalDivider(
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = "Click on one of the IDs above to contact the developer of this application.",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .padding(start = 32.dp , top = 32.dp , end = 32.dp),
                lineHeight = 32.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

        }

}