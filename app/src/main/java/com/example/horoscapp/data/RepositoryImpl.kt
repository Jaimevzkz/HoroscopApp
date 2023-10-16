package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService): Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching {
            apiService.getHosroscopeSign(sign)
        }
            .onSuccess {
                return it.toDomain()
            }
            .onFailure {
                Log.i("Jaime", "An error ocurred while using apiService, ${it.message}")
            }
        return null
    }
}