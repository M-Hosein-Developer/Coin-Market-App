package com.example.coinmarket.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.MainToolbar
import com.example.coinmarket.ui.style.Style

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

        MainToolbar(stringResource(R.string.help)){
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

@Composable
fun HelpText(title: String, desc: String) {


    Column(
        Modifier.padding(horizontal = 18.dp)
    ) {

        Text(
            text = title,
            style = Style.xLargeBoldTextStyle.copy(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(top = 12.dp),
        )

        Text(
            text = desc,
            style = Style.largeLightTextStyle.copy(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(top = 12.dp)
        )

    }

}