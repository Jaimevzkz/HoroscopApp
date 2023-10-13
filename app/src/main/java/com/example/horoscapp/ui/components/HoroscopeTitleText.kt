package com.example.horoscapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeTitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.padding(8.dp),
        color = secondary,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        fontFamily = FontFamily(Font(R.font.dancing))
    )
}