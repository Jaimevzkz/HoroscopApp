package com.example.horoscapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.primaryDark
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
    contentColor: Color
) {
    Text(
        text = text,
        modifier = modifier.padding(8.dp),
        color = contentColor,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        fontFamily = FontFamily(Font(R.font.dancing))
    )
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHoroscopeTopAppBar() {
    HoroscappTheme {
        HoroscopeTopAppBar(
            text = "Top App Bar",
            contentColor = secondary
        )
    }
}