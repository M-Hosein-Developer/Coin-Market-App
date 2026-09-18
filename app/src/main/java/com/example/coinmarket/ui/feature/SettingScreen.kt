package com.example.coinmarket.ui.feature

import android.app.Activity
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinmarket.R
import com.example.coinmarket.ui.MainToolbar
import com.example.coinmarket.ui.style.Style
import com.example.coinmarket.ui.theme.Gradient2
import com.example.coinmarket.viewModel.ThemeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController, viewModel: ThemeViewModel) {

    //Bottom Sheet state
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var language by rememberSaveable { mutableStateOf(R.string.english) }

    //Switch
    val checked = remember { mutableStateOf(false) }
    val context = LocalContext.current as? Activity ?: return

    if (viewModel.getSavedLanguage() == "en") language = R.string.english
    else language = R.string.persian

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(stringResource(R.string.setting)){ navController.popBackStack() }

        Language(language) { showBottomSheet = true }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        DynamicTheme(viewModel){
            viewModel.switchState = it
            viewModel.insertDynamicThemeStateRep()
            context.recreate()
        }

        HorizontalDivider(
            Modifier.padding(vertical = 12.dp)
        )

        //Bottom Sheet
        LanguageBottomSheet(
            sheetState = sheetState ,
            scope = scope ,
            showBottomSheet = showBottomSheet,
            onLanguageClicked = {
                viewModel.saveLanguagePreference(it)
                if (it == "en") language = R.string.english
                else language = R.string.persian
                context.recreate()
            },
            onDismissRequest = {
                showBottomSheet = it
            }
        )
    }

    checked.value = viewModel.themeState.collectAsState().value.dynamicThemeState

}

@Composable
fun Language(language: Int, onLanguageClicked: () -> Unit) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 18.dp)
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
                text = stringResource(R.string.language),
                modifier = Modifier
                    .padding(start = 12.dp),
                style = Style.xNormalTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )

        }

        Text(
            text = stringResource(language),
            style = Style.xNormalTextStyle.copy(Gradient2)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(sheetState: SheetState, scope: CoroutineScope, showBottomSheet: Boolean , onLanguageClicked: (String) -> Unit,  onDismissRequest :(Boolean) -> Unit) {

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
                            onLanguageClicked("en")
                            onDismissRequest.invoke(false)
                        }
                    }
                }) {
                    Text(stringResource(R.string.english) , style = Style.baseTextStyle)
                }

                TextButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onLanguageClicked("fa")
                            onDismissRequest.invoke(false)
                        }
                    }
                }) {
                    Text(stringResource(R.string.persian) , style = Style.baseTextStyle)
                }
            }
        }
    }
}

@Composable
fun DynamicTheme(viewModel: ThemeViewModel, onChangeClicked:(Boolean) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
    ) {

        Row(
            Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Text(
                text = stringResource(R.string.dynamic_Theme),
                modifier = Modifier
                    .padding(start = 12.dp),
                style = Style.xNormalTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
            )

            Switch(
                checked = viewModel.switchState,
                onCheckedChange = {
                    onChangeClicked.invoke(it)
                },
                thumbContent = if (viewModel.switchState) {
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
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )

        }

        Text(
            text = stringResource(R.string.dynamic_Theme_text),
            modifier = Modifier
                .padding(start = 12.dp, top = 8.dp , end = 42.dp),
            style = Style.baseTextStyle.copy(color = MaterialTheme.colorScheme.onBackground)
        )
    }
}