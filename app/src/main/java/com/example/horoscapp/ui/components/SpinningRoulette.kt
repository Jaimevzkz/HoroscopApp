package com.example.horoscapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.horoscapp.R
import kotlinx.coroutines.delay

@Composable
fun SpinningRoulette(
    modifier: Modifier = Modifier,
    onEndAnimation: ()->Unit
) {
    // This method should animate the roulette to rotate and throw the next animation when it finishes
    var rotation by remember{
        mutableFloatStateOf(0f)
    }
    val rotationAnimated by animateFloatAsState(
        targetValue = rotation,
        label = "",
        animationSpec = tween(durationMillis = 3000)
    )

    LaunchedEffect(Unit){
        rotation = 720f
    }

    var verticalBias by remember{
        mutableFloatStateOf(0f)
    }

    val verticalBiasAnimated by animateFloatAsState(
        targetValue = verticalBias,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 1000
        ),
        label = ""
    )

    var alpha by remember{
        mutableFloatStateOf(0f)
    }
    
    val alphaAnimated by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    LaunchedEffect(rotationAnimated){
        if(rotationAnimated == 720f){
            verticalBias = -1f
            alpha = 1f
        }
    }
    
    LaunchedEffect(verticalBiasAnimated){
        if(verticalBiasAnimated == -1f){
            delay(300)
            onEndAnimation()
        }
    }

    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.roulette),
            contentDescription = "Lucky roulette",
            modifier = Modifier
                .fillMaxSize()
                .offset(y = maxHeight / 2)
                .rotate(rotationAnimated)
        )

        val defaultOffset = remember(maxWidth){
            ((maxWidth/7) * 0.598f)
        }
        
        Image(
            painter = painterResource(id = R.drawable.card_back_small),
            contentDescription = null,
            modifier = Modifier
                .width(maxWidth / 7)
                .aspectRatio(0.598f)
                .align(BiasAlignment(0f, verticalBiasAnimated))
                .offset(y = defaultOffset)
                .alpha(alphaAnimated)

        )
    }

}

@Preview
@Composable
private fun SpinningRoulettePreview(){

    SpinningRoulette(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onEndAnimation = {}
    )

}