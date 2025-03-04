package com.example.coinmarket.viewModel

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.EmptyCoinListBook
import com.example.coinmarket.util.EmptyDollar
import com.example.coinmarket.util.NetworkChecker
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.usecases.mainUsecase.MainUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usecase: MainUsecase, context: Context) : ViewModel() {

    val getCryptoList = mutableStateOf(EmptyCoinList)
    val getCryptoBookmarkList = mutableStateOf(EmptyCoinListBook)
    val getDollarPrice = MutableStateFlow<PriceEntity>(EmptyDollar)
    val search = mutableStateOf("")

    init {
        if (NetworkChecker(context).internetConnection){
            getCryptoList()
        }else{
            getCryptoListFromDb()
        }
        getCryptoById(-1){}
        getDollarPrice()
        getCryptoBookmarkList()
    }

    @VisibleForTesting
    fun getCryptoList() {
        viewModelScope.launch {
            usecase.getCryptoList()
                .catch {  }
                .collect {
                    getCryptoList.value = it
                }
        }
    }

    @VisibleForTesting
    fun getCryptoListFromDb() {

        viewModelScope.launch {
            usecase.getCryptoListFromDb()
                .catch { }
                .collect {
                    getCryptoList.value = it
                }
        }

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