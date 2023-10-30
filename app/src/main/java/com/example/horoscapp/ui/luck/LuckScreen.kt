package com.example.horoscapp.ui.luck

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.horoscapp.R
import com.example.horoscapp.ui.BACKGROUND_COLOR
import com.example.horoscapp.ui.components.HoroscopeBodyText
import com.example.horoscapp.ui.components.SpinningRoulette
import com.example.horoscapp.ui.data.providers.RandomCardProvider
import com.example.horoscapp.ui.theme.HoroscappTheme

@Composable
fun LuckScreen(
    modifier: Modifier = Modifier,
    backGroundColor: Color,
    randomCardProvider: RandomCardProvider = RandomCardProvider(),
    onShareClicked: (String) -> Unit
) {
    var rouletteSpined by remember { mutableStateOf(false) }
    if (rouletteSpined) {
        ShowLuck(modifier.background(backGroundColor), randomCardProvider) {
            onShareClicked(it)
        }
    } else {
        SpinRoulette(modifier.background(backGroundColor)) {
            rouletteSpined = true
        }
    }

}

@Composable
private fun SpinRoulette(
    modifier: Modifier,
    onSpin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpinningRoulette(
            modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onSpin() }
        )
    }
}

@Composable
fun ShowLuck(
    modifier: Modifier = Modifier,
    randomCardProvider: RandomCardProvider,
    onShareClicked: (String) -> Unit
) {
    val luck = remember { randomCardProvider.getLucky()!! }
    val text = stringResource(id = luck.text)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(id = luck.img),
            contentDescription = "Lucky card",
            modifier = Modifier.size(400.dp)
        )
        Box(
            modifier = Modifier
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            HoroscopeBodyText(text = text)
        }
        Spacer(modifier = Modifier.weight(1f))
        HoroscopeBodyText(
            text = stringResource(id = R.string.share),
            modifier = Modifier
                .padding(24.dp)
                .clickable { onShareClicked(text) }
        )
    }
}

@Preview
@Composable
private fun LuckScreenPreview() {
    HoroscappTheme {
        ShowLuck(Modifier.background(BACKGROUND_COLOR), RandomCardProvider()) {}
    }
}