package com.example.horoscapp.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
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


const val ROTATION_DEGREES_ROULETTE = 720F
const val ROTATION_DURATION = 3000
const val VERTICAL_SLIDE_DURATION = 2000
const val VERTICAL_SLIDE_DELAY = 1000
const val CARD_RATIO = 0.598f
const val ALPHA_DURATION = 1000
const val CARD_SIZE_ANIMATION_DURATION = 2000

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
        animationSpec = tween(durationMillis = ROTATION_DURATION)
    )

    var verticalBias by remember{
        mutableFloatStateOf(0f)
    }

    val verticalBiasAnimated by animateFloatAsState(
        targetValue = verticalBias,
        animationSpec = tween(
            durationMillis = VERTICAL_SLIDE_DURATION,
            delayMillis = VERTICAL_SLIDE_DELAY
        ),
        label = ""
    )

    var alpha by remember{
        mutableFloatStateOf(0f)
    }
    
    val alphaAnimated by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = ALPHA_DURATION),
        label = ""
    )



    BoxWithConstraints(
        modifier = modifier
    ) {
        val defaultOffset = remember(maxWidth){
            ((maxWidth/7) * CARD_RATIO)
        }

        var cardWidth by remember{
            mutableStateOf(maxWidth / 7)
        }

        val cardWidthAnimated by animateDpAsState(
            targetValue = cardWidth,
            animationSpec = tween(durationMillis = CARD_SIZE_ANIMATION_DURATION),
            label = ""
        )

        LaunchedEffect(rotationAnimated){
            if(rotationAnimated == ROTATION_DEGREES_ROULETTE){
                verticalBias = -2.5f
                alpha = 1f
            }
        }

        LaunchedEffect(verticalBiasAnimated){
            if(verticalBiasAnimated == -2.5f){
                delay(100)
                cardWidth = (maxWidth / 7) * 2
            }
        }

        LaunchedEffect(cardWidthAnimated){
            if(cardWidthAnimated == (maxWidth / 7) * 2){
                delay(300)
                onEndAnimation()
            }
        }

        Image(
            painter = painterResource(id = R.drawable.roulette),
            contentDescription = "Lucky roulette",
            modifier = Modifier
                .fillMaxSize()
                .offset(y = maxHeight/2)
                .rotate(rotationAnimated)
                .clickable { rotation = ROTATION_DEGREES_ROULETTE }
        )

        Image(
            painter = painterResource(id = R.drawable.card_back_small),
            contentDescription = null,
            modifier = Modifier
                .width(cardWidthAnimated)
                .aspectRatio(CARD_RATIO)
                .align(BiasAlignment(0f, verticalBiasAnimated))
                .offset(y = defaultOffset)
                .alpha(alphaAnimated)
        )
    }

}

@Preview(showSystemUi = true)
@Composable
private fun SpinningRoulettePreview(){

    SpinningRoulette(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onEndAnimation = {}
    )

}