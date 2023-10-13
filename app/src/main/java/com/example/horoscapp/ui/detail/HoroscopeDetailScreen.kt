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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.Aries
import com.example.horoscapp.domain.model.HoroscopeModel.Cancer
import com.example.horoscapp.domain.model.HoroscopeModel.Gemini
import com.example.horoscapp.domain.model.HoroscopeModel.Leo
import com.example.horoscapp.domain.model.HoroscopeModel.Libra
import com.example.horoscapp.domain.model.HoroscopeModel.Pisces
import com.example.horoscapp.domain.model.HoroscopeModel.Sagittarius
import com.example.horoscapp.domain.model.HoroscopeModel.Scorpio
import com.example.horoscapp.domain.model.HoroscopeModel.Taurus
import com.example.horoscapp.domain.model.HoroscopeModel.Virgo
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
    val horoscopeModel = horoscopeModelArg.toHoroscopeModel()
    Column(modifier = modifier.background(backGroundColor)) {
        IconButton(onClick = onBackClicked, modifier = modifier.padding(top = 30.dp, bottom = 10.dp, start = 30.dp, end = 10.dp).size(30.dp)) {
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
                    .height(380.dp)
                    .padding(10.dp),
                painter = painterResource(id = R.drawable.detail_aquarius),
                contentDescription = "Hola"
            )
            HoroscopeTitleText(text = horoscopeModelArg)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                HoroscopeBodyText(
                    text = "ajjfkjwelkfj wlkewmfwlkef wkjelfkwjefklw lwkefjlkwejflk welkfjwlkefwlke wekjf2'pejfw wlkflkwf kejfwlkefkqñwe wlkefjlwkefjñlkqe walkejflqkwñejf ejkfljwqlkjeflkqwev aaja"
                )
            }
        }
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
private fun detailScreenPreview() {
    HoroscopeDetailScreen(
        horoscopeModelArg = "Aries",
        backGroundColor = BACKGROUND_COLOR,
        onBackClicked = {}
    )
}