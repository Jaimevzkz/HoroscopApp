package com.example.horoscapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.ui.theme.secondary


const val ROTATION_DEGREES_ITEM = 720f
const val ROTATION_TIME = 1000

@Composable
fun HoroscopeItem(horoscopeInfo: HoroscopeInfo, modifier: Modifier = Modifier, onItemSelected: (String) -> Unit) {
    val arg = stringResource(id = horoscopeInfo.name)
    var rotation by remember{
        mutableFloatStateOf(0f)
    }
    val rotationAnimated by animateFloatAsState(
        targetValue = rotation,
        label = "",
        animationSpec = tween(durationMillis = ROTATION_TIME)
    )
    LaunchedEffect(rotationAnimated){
        if(rotationAnimated == ROTATION_DEGREES_ITEM){
            onItemSelected(arg)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.selectable(
            selected = false,
            onClick = { rotation = ROTATION_DEGREES_ITEM }
        )
    ) {

        Image(
            painter = painterResource(id = horoscopeInfo.img),
            contentDescription = arg,
            modifier
                .size(230.dp)
                .rotate(rotationAnimated)
        )
        Text(
            text = stringResource(id = horoscopeInfo.name),
            color = secondary,
            modifier = modifier,
            fontFamily = FontFamily(Font(R.font.dancing)),
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun HoroscopeItemPreview() {
    HoroscopeItem(horoscopeInfo = HoroscopeInfo.Aries){}
}