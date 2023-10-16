package com.example.horoscapp.ui.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*
import com.example.horoscapp.ui.BACKGROUND_COLOR
import com.example.horoscapp.ui.components.HoroscopeBodyText
import com.example.horoscapp.ui.components.HoroscopeTitleText
import com.example.horoscapp.ui.theme.gold

@Composable
fun HoroscopeDetailScreen(
    modifier: Modifier = Modifier,
    horoscopeModelArg: String,
    horoscopeDetailViewModel: HoroscopeDetailViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    backGroundColor: Color
) {
    //DrawUI(modifier, backGroundColor, onBackClicked)
    val horoscopeModel = horoscopeModelArg.toHoroscopeModel()
    val state by horoscopeDetailViewModel.state.collectAsState()
    horoscopeDetailViewModel.getPrediction(horoscopeModel)

    when (state) {
        is HoroscopeDetailState.Success -> DrawUI(
            modifier = modifier,
            backGroundColor = backGroundColor,
            onBackClicked = onBackClicked,
            state = (state as HoroscopeDetailState.Success)
        )

        is HoroscopeDetailState.Loading -> DrawProgressbar(
            modifier = modifier,
            backGroundColor = backGroundColor
        )

        is HoroscopeDetailState.Error -> DrawError(
            (state as HoroscopeDetailState.Error).error
        )
    }
}

@Composable
private fun DrawUI(
    modifier: Modifier,
    backGroundColor: Color,
    onBackClicked: () -> Unit,
    state: HoroscopeDetailState.Success
) {
    val image = when (state.horoscopeModel) {
        Aries -> R.drawable.detail_aries
        Taurus -> R.drawable.detail_taurus
        Gemini -> R.drawable.detail_gemini
        Cancer -> R.drawable.detail_cancer
        Leo -> R.drawable.detail_leo
        Virgo -> R.drawable.detail_virgo
        Libra -> R.drawable.detail_libra
        Scorpio -> R.drawable.detail_scorpio
        Sagittarius -> R.drawable.detail_sagittarius
        Pisces -> R.drawable.detail_pisces
    }
    Column(modifier = modifier.background(backGroundColor)) {
        IconButton(
            onClick = onBackClicked,
            modifier = modifier
                .padding(top = 30.dp, bottom = 10.dp, start = 30.dp, end = 10.dp)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                tint = gold,
                contentDescription = "Navigate Back",
                modifier = Modifier.size(30.dp)
            )
        }
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .padding(10.dp),
                painter = painterResource(id = image),
                contentDescription = "Hola"
            )
            HoroscopeTitleText(text = state.sign)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                HoroscopeBodyText(
                    text = state.prediction
                )
            }
        }
    }
}

@Composable
private fun DrawProgressbar(modifier: Modifier = Modifier, backGroundColor: Color) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backGroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = gold
        )
    }
}

@Composable
private fun DrawError(error: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = error)
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

@Preview
@Composable
private fun DetailScreenPreview() {
    val state = HoroscopeDetailState.Success(
        "Aries",
        "asljdkakfla sakdlfakdfaldsf asdkljfalksfl lskdjfadskflaslkdfjaksldfj akdljfalksdfkldajvl aslkdjaksdjfkadfj askdljflkasdjflkad askdlfaksdjfenbvdsb asdklcjadsnvaslkdnv",
        Aries
    )
    DrawUI(
        modifier = Modifier,
        backGroundColor = BACKGROUND_COLOR,
        onBackClicked = {},
        state = state
    )
}