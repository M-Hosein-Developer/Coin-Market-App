package ir.android.bookmark.ui

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
import ir.android.bookmark.ui.component.CryptoBookmarkList
import ir.android.ui.R
import ir.android.ui.component.MainToolbar
import ir.android.ui.navigation.MyScreens
import ir.android.ui.theme.CoinMarketTheme
import ir.android.ui.theme.LocalNavigation
import ir.android.ui.util.SingleEventEffect

@Composable
fun BookmarkScreen() {
    val vm = hiltViewModel<BookmarkViewModel>()
    val state by vm.uiState.collectAsStateWithLifecycle()
    val onAction = vm::onAction
    val nav = LocalNavigation.current

    LaunchedEffect(Unit) {
        vm.onLaunch()
    }

    SingleEventEffect(sideEffectFlow = vm.sideEffect) { effect ->
        when (effect) {
            BookmarkContract.Effect.NavBack -> nav.popBackStack()
            is BookmarkContract.Effect.NavToDetail ->
                nav.navigate(MyScreens.DetailScreen.route + "/" + effect.id)
        }
    }

    BookmarkContent(
        state = state,
        onAction = onAction
    )
}

@Composable
private fun BookmarkContent(
    state: BookmarkContract.State,
    onAction: (BookmarkContract.Action) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        MainToolbar(
            text = stringResource(R.string.bookmark),
            onBackPress = { onAction(BookmarkContract.Action.BackClicked) }
        )

        CryptoBookmarkList(
            coinList = state.bookmarkList,
            onItemClicked = { onAction(BookmarkContract.Action.DetailClicked(it)) }
        )
    }
}

@Preview
@Composable
private fun BookmarkContentPreview() {
    CoinMarketTheme {
        BookmarkContent(
            state = BookmarkContract.State(),
            onAction = {}
        )
    }
}

