package com.example.horoscapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeItem(horoscopeInfo: HoroscopeInfo, modifier: Modifier = Modifier, onItemSelected: (String) -> Unit) {
    val arg = stringResource(id = horoscopeInfo.name)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(
            selected = false,
            onClick = { onItemSelected(arg) }
        )
    ) {
        Image(
            painter = painterResource(id = horoscopeInfo.img),
            contentDescription = stringResource(id = horoscopeInfo.name),
            modifier.size(230.dp)
        )
        Text(
            text = stringResource(id = horoscopeInfo.name),
            color = secondary,
            modifier = modifier,
            fontFamily = FontFamily(Font(R.font.dancing)),
            fontSize = 30.sp
        )
    }
}

@Preview()
@Composable
fun HoroscopeItemPreview() {
    HoroscopeItem(horoscopeInfo = HoroscopeInfo.Aries){}
}