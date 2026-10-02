package com.example.coinmarket.viewModel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ir.android.ui.util.EmptyCoin
import ir.android.ui.util.EmptyCoinList
import ir.androidcoder.Model.CryptoCurrency
import ir.androidcoder.Model.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

//    private lateinit var repository: IHomeRepository

    @Mock
    private lateinit var usecase: MainUsecase

    @Mock
    private lateinit var context : Context

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
//        usecase = GetCryptoListUsecase(repository)
        viewModel = HomeViewModel(usecase, context)
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
        val mockEntity = flow<CryptoCurrency> {
            CryptoCurrency(
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
    }

    @Test
    fun `when call getDollarPrice should get dollar price`() = runTest{
        val mockEntity = flow<Price> {
            Price("100", "100", "100", "100")
        }
        `when`(usecase.getDollarPrice()).thenReturn(mockEntity)

        viewModel.getDollarPrice()
        advanceUntilIdle()

        val data = viewModel.getDollarPrice.value
        mockEntity.collect{
            assertEquals(it , data)
        }
    }

    @Test
    fun `When call insertBookmark should insert a crypto in data base`() = runTest {
        val mockEntity = EmptyCoin

        viewModel.insertBookmark(mockEntity)

        advanceUntilIdle()

        verify(usecase, times(1)).insertBookmark(mockEntity)
    }

    @Test
    fun `when call getCryptoBookmarkList should get list of bookmark crypto`() = runTest {
        val mockEntity = flow { emit(EmptyCoinList)}
        `when`(usecase.getBookmarkList()).thenReturn(mockEntity)

        viewModel.getCryptoBookmarkList()
        advanceUntilIdle()

        val data = viewModel.getCryptoList.value
        mockEntity.collect{
            assertEquals(it , data)
        }
    }

    @Test
    fun `when call deleteBookmark should delete coin with id from data base`() = runTest {
        val mockEntity = 1

        viewModel.deleteBookmark(1)

        advanceUntilIdle()

        verify(usecase, times(1)).deleteBookmark(mockEntity)
    }

}