package ir.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ir.android.ui.R
import ir.android.ui.navigation.MyScreens
import ir.android.ui.style.Style
import ir.android.ui.theme.CoinMarketTheme
import ir.android.ui.theme.Gradient1
import ir.android.ui.theme.Gradient2
import ir.android.ui.theme.Gradient3
import ir.android.ui.theme.LocalNavigation
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    content: @Composable () -> Unit = {}
) {
    val nav = LocalNavigation.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.40f)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Gradient1,
                                    Gradient2,
                                    Gradient3
                                )
                            )
                        ),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    AsyncImage(
                        model = R.drawable.the_crypto_app,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 12.dp, bottom = 12.dp)
                            .size(100.dp)
                    )

                    Text(
                        text = stringResource(R.string.coin_market_drawer),
                        style = Style.whiteVeryLargeTextStyle,
                        modifier = Modifier.padding(start = 12.dp, bottom = 24.dp)
                    )

                    Text(
                        text = stringResource(R.string.coin_market_drawer_desc),
                        style = Style.whiteNormalLightTextStyle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, bottom = 24.dp, end = 24.dp),
                    )

                }

                HorizontalDivider()

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.home),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = null) },
                    onClick = {
                        nav.navigate(MyScreens.HomeScreen.route) {
                            popUpTo(MyScreens.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.bookmark),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_bookmark_border_24),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        nav.navigate(MyScreens.BookmarkScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.setting),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        nav.navigate(MyScreens.SettingScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.info),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) },
                    onClick = {
                        nav.navigate(MyScreens.InfoScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.help),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_help_outline_24),
                            contentDescription = null
                        )
                    },
                    onClick = {
                        nav.navigate(MyScreens.HelpScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = stringResource(R.string.logout),
                            style = Style.baseTextStyle
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                            contentDescription = null
                        )
                    },
                    onClick = {
                        nav.navigate(MyScreens.IntroScreen.route)
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        }
    ) {
        content()
    }
}

@Preview
@Composable
private fun NavigationDrawerPreview() {
    CoinMarketTheme {
        NavigationDrawer()
    }
}