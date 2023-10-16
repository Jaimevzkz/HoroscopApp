package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    object Loading: HoroscopeDetailState()
    data class Error(val error: String): HoroscopeDetailState()
    data class Success(val sign: String, val prediction: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
}