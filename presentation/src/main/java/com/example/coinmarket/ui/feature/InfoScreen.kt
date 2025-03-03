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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.MainToolbar
import com.example.coinmarket.ui.style.Style
import com.example.coinmarket.ui.theme.introTextColor

@Composable
fun InfoScreen(navController: NavHostController) {

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(stringResource(R.string.info)){ navController.popBackStack() }

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
                    style = Style.XXXLargeBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(start = 32.dp, top = 12.dp)
                )

                Text(
                    text = "Cap",
                    color = introTextColor,
                    style = Style.XXXLargeBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 12.dp)
                )

            }

            Column {

                Text(
                    text = "${stringResource(R.string.description)}: ",
                    style = Style.xNormalBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp)
                )

                Text(
                    text = stringResource(R.string.desc_text),
                    style = Style.xNormalBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(start = 32.dp, top = 18.dp, end = 32.dp),
                    lineHeight = 32.sp,
                )

            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 32.dp)
            )

            Row {

                Text(
                    text = "${stringResource(R.string.email)}: ",
                    style = Style.xNormalBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp),
                )

                Text(
                    text = stringResource(R.string.email_address),
                    style = Style.xNormalLightTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable { onEmailClicked.invoke("m.hosein.developer@gmail.com") }
                )

            } // Email

            Row {

                Text(
                    text = "${stringResource(R.string.linkedIn)}: ",
                    style = Style.xNormalBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 32.dp, start = 32.dp)
                )

                Text(
                    text = stringResource(R.string.linkedIn_address),
                    style = Style.xNormalLightTextStyle.copy(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .clickable { onLinkedInClicked.invoke("mohammad-hosein-hajiakbari-662337246") }
                )

            } // LinkedIn
        }
}