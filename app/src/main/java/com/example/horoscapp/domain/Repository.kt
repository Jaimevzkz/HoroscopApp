package com.example.horoscapp.domain

import com.example.horoscapp.domain.model.PredictionModel

interface Repository { //Interface that serves the purpose of being a bridge between data and business layer
    suspend fun getPrediction(sign: String): PredictionModel?
}