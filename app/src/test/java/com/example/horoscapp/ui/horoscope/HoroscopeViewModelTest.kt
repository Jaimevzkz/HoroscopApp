package com.example.horoscapp.ui.horoscope

import com.example.horoscapp.HoroscopeModelObject.horoscopeInfoList
import com.example.horoscapp.data.providers.HoroscopeProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider


    private lateinit var viewModel: HoroscopeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

    }


    @Test
    fun `when viewmodel is created then horoscopes are loaded`() {
        every {
            horoscopeProvider.getHoroscopes()
        } returns horoscopeInfoList
        viewModel = HoroscopeViewModel(horoscopeProvider)

        val horoscopes = viewModel.horoscope.value
        assert(horoscopes.isNotEmpty())
    }

}