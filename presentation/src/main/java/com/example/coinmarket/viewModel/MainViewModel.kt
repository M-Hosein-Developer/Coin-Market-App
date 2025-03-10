package com.example.coinmarket.viewModel

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.Query
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.EmptyCoinListBook
import com.example.coinmarket.util.EmptyDollar
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.usecases.mainUsecase.MainUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usecase: MainUsecase) : ViewModel() {

    val getCryptoBookmarkList = mutableStateOf(EmptyCoinListBook)
    val getCryptoListFormDatabase = mutableStateOf<Flow<PagingData<CryptoCurrencyEntity>>?>(null)
    val getDollarPrice = MutableStateFlow(EmptyDollar)
    val search = mutableStateOf("")

    init {
        getCryptoById(-1){}
        getDollarPrice()
        getCryptoBookmarkList()
    }



    val getCryptoListFormServer = usecase.getCryptoListFormServer().cachedIn(viewModelScope)

    fun getCryptoListFormDatabase(){
        getCryptoListFormDatabase.value = usecase.getCryptoListFormDatabase(search.value)
            .cachedIn(viewModelScope)
    }

    fun getCryptoById(id: Int , getCryptoById :(CryptoCurrencyEntity) -> Unit) {
        viewModelScope.launch {
            usecase.getCryptoByIdFromDb(id)
                .catch {  }
                .collect{
                    getCryptoById.invoke(it)
                }
        }
    }

    @VisibleForTesting
    fun getDollarPrice() = viewModelScope.launch {
        usecase.getDollarPrice()
            .catch {}
            .collect{
                getDollarPrice.value = it
            }
    }

    fun insertBookmark(data: CryptoCurrencyEntity) = viewModelScope.launch{
        usecase.insertBookmark(data)
    }

    @VisibleForTesting
    fun getCryptoBookmarkList() = viewModelScope.launch {
        usecase.getBookmarkList()
            .catch {}
            .collect{
                getCryptoBookmarkList.value = it
            }
    }

    fun deleteBookmark(id: Int) = viewModelScope.launch {
        usecase.deleteBookmark(id)
    }

}