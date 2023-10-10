package com.example.horoscapp.ui.horoscope

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.components.HoroscopeItem
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.primary

@Composable
fun HoroscopeScreen(
    modifier: Modifier = Modifier,
    horoscopeViewModel: HoroscopeViewModel = hiltViewModel(),
    onItemSelected: (String) -> Unit,
    backGroundColor: Color
) {
    val horoscopeList by horoscopeViewModel.horoscope.collectAsState()

    Surface(modifier = modifier.fillMaxSize(), color = backGroundColor) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.wrapContentHeight()
        ) {
            items(horoscopeList) { individualItemInfo ->
                HoroscopeItem(horoscopeInfo = individualItemInfo){horoscopeSelected ->
                    onItemSelected(horoscopeSelected)
                }
            }
        }
    }
}

//Previews
@Preview
@Composable
private fun HoroscopeScreenPreview() {
    HoroscappTheme {
        HoroscopeScreen(backGroundColor = primary, onItemSelected = {})
    }
}

