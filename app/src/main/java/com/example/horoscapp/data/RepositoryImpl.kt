package com.example.horoscapp.data

import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService): Repository {
    override suspend fun getPrediction(sign: String) {
        TODO("Not yet implemented")
    }
}