package com.example.coinmarket.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinmarket.util.EmptyCoinList
import com.example.coinmarket.util.EmptyCoinListBook
import com.example.coinmarket.util.NetworkChecker
import com.example.coinmarket.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.entities.PriceEntity
import ir.androidcoder.usecases.mainUsecase.MainUsecaseImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usecase: MainUsecaseImpl, context: Context) : ViewModel() {

    val getCryptoList = mutableStateOf(EmptyCoinList)
    val getCryptoBookmarkList = mutableStateOf(EmptyCoinListBook)
    val getDollarPrice = MutableStateFlow<PriceEntity?>(null)
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

    private fun getCryptoList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            usecase.getCryptoList
                .catch { Log.v("error", "Error -> " + it.message) }
                .collect {
                    getCryptoList.value = it
                    Log.v("testDataFromVar1" , it.toString())
                }
        }
    }

    private fun getCryptoListFromDb() {

        viewModelScope.launch {
            usecase.getCryptoListFromDb
                .catch { Log.v("errorDb", "Error -> " + it.message) }
                .collect {
                    getCryptoList.value = it
                    Log.v("testDataFromVar12" , it.toString())

                }
        }

    }

    fun getCryptoById(id: Int , getCryptoById :(CryptoCurrencyEntity) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            while (true) {
                getCryptoById.invoke(usecase.getCryptoByIdFromDb(id))
                delay(4000)
            }
        }
    }

    private fun getDollarPrice() = viewModelScope.launch {
        usecase.getDollarPrice
            .catch {
                Log.v("error", "Error -> " + it.message)
            }.collect{
                getDollarPrice.value = it
                Log.v("testPrice1" , it.toString())
            }
    }

    fun insertBookmark(data: CryptoCurrencyEntity) = viewModelScope.launch(
        coroutineExceptionHandler) {
        usecase.insertBookmark(data)
    }

    private fun getCryptoBookmarkList() = viewModelScope.launch(coroutineExceptionHandler) {
        usecase.getBookmarkList
            .catch {
                Log.v("error", "Error -> " + it.message)
            }.collect{
                getCryptoBookmarkList.value = it
            }
    }

    fun deleteBookmark(id: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        usecase.deleteBookmark(id)
    }

}