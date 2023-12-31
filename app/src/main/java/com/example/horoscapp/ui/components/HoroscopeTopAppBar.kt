package com.example.horoscapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeTopAppBar(
    modifier: Modifier = Modifier,
    text: String
) {
    HoroscopeTitleText(text = text, modifier = modifier)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHoroscopeTopAppBar() {
    HoroscappTheme {
        HoroscopeTopAppBar(
            text = "Top App Bar",
            modifier = Modifier.padding(8.dp)
        )
    }
}