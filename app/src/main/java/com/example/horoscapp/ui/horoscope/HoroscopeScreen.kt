package com.example.horoscapp.ui.horoscope

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.primary

@Composable
fun HoroscopeScreen(
    horoscopeViewModel: HoroscopeViewModel = HoroscopeViewModel(),
    horoscopeInfoList: List<HoroscopeInfo> = emptyList() ,//TODO
    modifier: Modifier = Modifier,
    backGroundColor: Color
) {
    Surface(modifier = modifier.fillMaxSize(), color = backGroundColor) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(horoscopeInfoList){

            }
        }
    }
}

//Previews
@Preview
@Composable
private fun HoroscopeScreenPreview() {
    HoroscappTheme {
       // HoroscopeScreen(backGroundColor = primary)
    }
}

