package com.example.horoscapp.ui.detail

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*

@Composable
fun HoroscopeDetailScreen(
    modifier: Modifier = Modifier,
    horoscopeModelArg: String,
    horoscopeDetailViewModel: HoroscopeDetailViewModel = hiltViewModel(),
    backGroundColor: Color
) {
    val horoscopeModel = horoscopeModelArg.toHoroscopeModel()
    Surface(modifier = modifier.fillMaxSize(), color = backGroundColor) {
        Text(text = horoscopeModelArg)
    }
}

private fun String.toHoroscopeModel(): HoroscopeModel = when (this) {
    "Aries" -> Aries
    "Taurus" -> Taurus
    "Gemini" -> Gemini
    "Cancer" -> Cancer
    "Leo" -> Leo
    "Virgo" -> Virgo
    "Libra" -> Libra
    "Scorpio" -> Scorpio
    "Sagittarius" -> Sagittarius
    "Pisces" -> Pisces
    else -> {
        Log.e("Jaime", "Error parsing argument to HoroscopeModel")
        Aries
    }
}