package com.example.coinmarket.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.coinmarket.R

@Composable
fun HelpScreen(navController: NavHostController) {

    val helpTitle = listOf(
        stringResource(R.string.faq_title_1),
        stringResource(R.string.faq_title_2),
        stringResource(R.string.faq_title_3),
        stringResource(R.string.faq_title_4),
        stringResource(R.string.faq_title_5),
        stringResource(R.string.faq_title_6)
        )
    val helpDesc = listOf(
        stringResource(R.string.faq_desc_1),
        stringResource(R.string.faq_desc_2),
        stringResource(R.string.faq_title_3),
        stringResource(R.string.faq_desc_4),
        stringResource(R.string.faq_desc_5),
        stringResource(R.string.faq_desc_6)
    )

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {

        HelpToolbar {
            navController.popBackStack()
        }

        for (i in helpTitle.indices) {

            HelpText(helpTitle[i] , helpDesc[i])
            HorizontalDivider(
                Modifier.padding(vertical = 18.dp)
            )

        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpToolbar(onBackPress :() -> Unit ){

    TopAppBar(
        title = { Text(
            text = stringResource(R.string.help),
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
fun HelpText(title: String, desc: String) {


    Column(
        Modifier.padding(horizontal = 18.dp)
    ) {

        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 12.dp),
            color = MaterialTheme.colorScheme.onBackground
        )


        Text(
            text = desc,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier.padding(top = 12.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

    }

}