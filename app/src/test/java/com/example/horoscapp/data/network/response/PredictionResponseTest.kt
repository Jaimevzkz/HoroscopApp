package com.example.horoscapp.data.network.response

import com.example.horoscapp.HoroscopeModelObject
import com.example.horoscapp.HoroscopeModelObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class PredictionResponseTest{
    @Test
    fun `toDomain should return a valid PredictionModel`(){
        //Given
        val horoscopeResponse = anyResponse
        //When
        val predictionModel = horoscopeResponse.toDomain()
        //Then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope

    }




}