package com.example.horoscapp.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state
    private lateinit var horoscope: HoroscopeModel

    fun getPrediction(horoscopeModel: HoroscopeModel) {
        horoscope = horoscopeModel
        viewModelScope.launch {
            //_state.value = HoroscopeDetailState.Loading
            val result = withContext(Dispatchers.IO) { getPredictionUseCase(horoscopeModel.name) }
            if (result != null) {
                Log.i("Jaime", "Retrofit Call")
                _state.value =
                    HoroscopeDetailState.Success(result.sign, result.horoscope, horoscope)
            } else {
                _state.value =
                    HoroscopeDetailState.Error("An error occurred, please try again later")
            }
        }
    }

}