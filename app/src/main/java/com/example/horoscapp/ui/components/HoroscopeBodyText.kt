package com.example.horoscapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeBodyText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        color = secondary,
        fontWeight = FontWeight.Normal,
        fontSize = 25.sp,
        fontFamily = FontFamily(Font(R.font.dancing))
    )
}