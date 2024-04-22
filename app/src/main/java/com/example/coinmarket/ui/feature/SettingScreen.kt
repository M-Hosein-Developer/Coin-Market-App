package com.example.coinmarket.ui.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {

    //Bottom Sheet state
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    //Switch

    var checked by remember { mutableStateOf(false) }


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 18.dp)
    ) {

        SettingToolbar { navController.popBackStack() }

        Language { showBottomSheet = true }

        DynamicTheme(checked){
            checked = it
        }


        //Bottom Sheet
        LanguageBottomSheet(sheetState , scope , showBottomSheet){ showBottomSheet = it }
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
fun Language(onLanguageClicked : () -> Unit) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onLanguageClicked.invoke() },
        verticalAlignment = Alignment.CenterVertically ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically ,
        ) {

            Icon(
                painter = painterResource(R.drawable.baseline_language_24),
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp),
            )

            Text(
                text = "Language",
                modifier = Modifier
                    .padding(start = 12.dp),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )

        }

        Text(
            text = "English",
            style = TextStyle(
                fontSize = 18.sp
            ),
            color = Gradient2
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(sheetState: SheetState, scope: CoroutineScope, showBottomSheet: Boolean ,  onDismissRequest :(Boolean) -> Unit) {

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onDismissRequest.invoke(false)
            },
            sheetState = sheetState
        ) {
            // Sheet content
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {

                TextButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismissRequest.invoke(false)
                        }
                    }
                }) {
                    Text("English")
                }

                TextButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismissRequest.invoke(false)
                        }
                    }
                }) {
                    Text("New languages will be added to this section soon!")
                }

            }


        }
    }

}

@Composable
fun DynamicTheme(checked: Boolean , onChangeClicked :(Boolean) -> Unit) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Dynamic Theme",
            modifier = Modifier
                .padding(start = 12.dp),
            style = TextStyle(
                fontSize = 18.sp
            )
        )


        Switch(
            checked = checked,
            onCheckedChange = {
                onChangeClicked.invoke(it)
                              },
            thumbContent = if (checked) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Gradient2,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )

    }
}