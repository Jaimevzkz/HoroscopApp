package com.example.horoscapp.domain

interface Repository { //Interface that serves the purpose of being a bridge between data and business layer
    suspend fun getPrediction(sign: String)

}