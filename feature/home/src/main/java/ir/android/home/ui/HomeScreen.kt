package ir.android.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.android.home.ui.component.CardSlider
import ir.android.home.ui.component.CoinList
import ir.android.ui.R
import ir.android.ui.component.MainToolbar
import ir.android.ui.navigation.MyScreens
import ir.android.ui.theme.CoinMarketTheme
import ir.android.ui.theme.LocalNavigation
import ir.android.ui.util.SingleEventEffect

@Composable
fun HomeScreen() {
    val vm = hiltViewModel<HomeViewModel>()
    val state by vm.uiState.collectAsStateWithLifecycle()
    val onAction = vm::onAction
    val nav = LocalNavigation.current

    LaunchedEffect(Unit) {
        vm.onLaunch()
    }

    SingleEventEffect(vm.sideEffect) { effect ->
        when (effect) {
            is HomeContract.Effect.NavBack -> nav.popBackStack()
            is HomeContract.Effect.NavToSearch -> nav.navigate(MyScreens.SearchScreen.route)
            is HomeContract.Effect.NavToDetail -> nav.navigate(MyScreens.DetailScreen.route + "/" + effect.id)
        }
    }

    HomeScreenContent(
        state = state,
        onAction = onAction
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeContract.State,
    onAction: (HomeContract.Action) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(
            text = stringResource(R.string.home),
            onBackPress = { onAction(HomeContract.Action.BackClicked) }
        )

        CardSlider(state.cryptoList)

        CoinList(
            coinList = state.cryptoList,
            onSearchClicked = { onAction(HomeContract.Action.SearchClicked) },
            onItemClicked = { onAction(HomeContract.Action.CryptoItemClicked(it)) }
        )
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    CoinMarketTheme {
        HomeScreenContent(
            state = HomeContract.State(),
            onAction = {}
        )
    }
}