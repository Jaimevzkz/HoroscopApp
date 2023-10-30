package com.example.horoscapp.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.horoscapp.R

@Composable
fun SpinningRoulette(modifier: Modifier = Modifier) {
    // This method should animate the roulette to rotate and throw the next animation when it finishes
    Image(
        painter = painterResource(id = R.drawable.half_roulette),
        contentDescription = "Lucky roulette",
        modifier = modifier
    )
}