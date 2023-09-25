package com.example.horoscapp.ui.horoscope

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.primary

@Composable
fun HoroscopeScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = primary) {

    }
}

@Preview
@Composable
private fun HoroscopeScreenPreview(){
    HoroscappTheme{
        HoroscopeScreen()
    }
}

