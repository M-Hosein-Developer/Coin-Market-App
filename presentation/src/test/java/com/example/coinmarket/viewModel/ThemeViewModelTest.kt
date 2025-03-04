package com.example.coinmarket.viewModel

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ir.androidcoder.entities.DynamicThemeEntity
import ir.androidcoder.usecases.themeUsecase.ThemeUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ThemeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var usecase: ThemeUsecase

    @Mock
    private lateinit var prefs: SharedPreferences

    private lateinit var viewModel: ThemeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ThemeViewModel(usecase, prefs)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insertDynamicThemeStateRep should call usecase with correct data`() = runTest {
        viewModel.switchState = true
        val expectedEntity = DynamicThemeEntity(1, true)

        viewModel.insertDynamicThemeStateRep()
        advanceUntilIdle()

        verify(usecase).insertDynamicThemeStateRep(expectedEntity)
    }

    @Test
    fun `getDynamicThemeState should update themeState and switchState`() = runTest {
        val mockEntity = DynamicThemeEntity(1, false)
        `when`(usecase.getDynamicThemeState()).thenReturn(mockEntity)

        viewModel.getDynamicThemeState()
        runCurrent()
        val data =viewModel.themeState.value

        assertEquals(mockEntity, data)
        assertFalse(viewModel.switchState)
    }

    @Test
    fun `saveLanguagePreference should save language in SharedPreferences`() {
        val editor = mock(SharedPreferences.Editor::class.java)
        `when`(prefs.edit()).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)

        val language = "en"
        viewModel.saveLanguagePreference(language)

        verify(editor).putString("language", language)
        verify(editor).apply()
    }

    @Test
    fun `getSavedLanguage should return saved language from SharedPreferences`() {
        `when`(prefs.getString("language", "fa")).thenReturn("en")

        val result = viewModel.getSavedLanguage()

        assertEquals("en", result)
    }
}