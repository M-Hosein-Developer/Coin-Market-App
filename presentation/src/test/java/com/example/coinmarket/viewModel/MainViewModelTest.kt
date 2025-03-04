package com.example.coinmarket.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coinmarket.util.EmptyCoinList
import ir.androidcoder.entities.CryptoCurrencyEntity
import ir.androidcoder.repositories.mainRepo.MainRepository
import ir.androidcoder.usecases.mainUsecase.MainUsecaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: MainRepository

    private lateinit var usecase: MainUsecaseImpl

    @Mock
    private lateinit var context : Context

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        usecase = MainUsecaseImpl(repository)
        viewModel = MainViewModel(usecase , context)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `getCryptoList should update list`() = runTest{
        val mockEntity = flow { emit(EmptyCoinList)}
        `when`(usecase.getCryptoList()).thenReturn(mockEntity)

        viewModel.getCryptoList()
        advanceTimeBy(5000)
        advanceUntilIdle()

        val data = viewModel.getCryptoList.value
        val entity = mockEntity.first()

        assertEquals(entity , data)
    }

    @Test
    fun `getCryptoListFromDb should update list from date base`() = runTest {
        val mockEntity= flow { emit(EmptyCoinList)}
        `when`(usecase.getCryptoListFromDb()).thenReturn(mockEntity)

        viewModel.getCryptoListFromDb()
        advanceTimeBy(5000)
        advanceUntilIdle()

        val data = viewModel.getCryptoList.value
        val entity = mockEntity.first()

        assertEquals(entity , data)
    }

    @Test
    fun `getCryptoById should get detail by id from db`() = runTest{
        val mockEntity = flow<CryptoCurrencyEntity>{CryptoCurrencyEntity(
            ath = 69000.0,
            atl = 67.81,
            badges = listOf(1, 1),
            circulatingSupply = 19000000.0,
            cmcRank = 1,
            dateAdded = "2013-04-28",
            high24h = 50000.0,
            id = 1,
            isActive = 1,
            isAudited = false,
            lastUpdated = "2024-03-04T12:00:00Z",
            low24h = 48000.0,
            marketPairCount = 300,
            maxSupply = 21000000.0,
            name = "Bitcoin",
            quotes = listOf(),
            selfReportedCirculatingSupply = 0.0,
            slug = "bitcoin",
            symbol = "BTC",
            totalSupply = 21000000.0
        )
        }

        `when`(usecase.getCryptoByIdFromDb(1)).thenReturn(mockEntity)


        viewModel.getCryptoById(1){
            assertEquals(mockEntity , it)
        }
        advanceUntilIdle()


    }





}