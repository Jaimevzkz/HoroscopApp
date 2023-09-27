package com.example.horoscapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.horoscapp.ui.HoroscDestinations
import com.example.horoscapp.ui.Horoscope
import com.example.horoscapp.ui.Luck
import com.example.horoscapp.ui.Palmistry
import com.example.horoscapp.ui.theme.accent
import com.example.horoscapp.ui.theme.primaryDark
import com.example.horoscapp.ui.theme.secondary

@Composable
fun HoroscopeBottomBar(
    allScreens: List<HoroscDestinations>,
    onTabSelected: (HoroscDestinations) -> (Unit),
    currentScreen: HoroscDestinations
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = primaryDark,

    ) {
        Row(Modifier.selectableGroup()) {
            allScreens.forEach { screen ->
                bottomBarButton(
                    text = screen.route,
                    icon = screen.icon,
                    onSelected = {
                        onTabSelected(screen)
                    },
                    modifier = Modifier.weight(1f),
                    selected = (currentScreen == screen)
                )
            }
        }
    }
}

@Composable
private fun bottomBarButton(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    modifier: Modifier,
    selected: Boolean
) {
    val color = if(selected) secondary else accent
    Column(
        modifier = modifier
            .selectable(
                onClick = onSelected,
                selected = selected
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //modifier = Modifier.wrapContentWidth()
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = color
        )
        Text(text = text, color = color)
    }
}

@Composable
@Preview()
private fun BottomBarButtonPreview() {
    bottomBarButton(
        "Palmistry",
        Icons.Filled.FrontHand,
        {},
        modifier = Modifier,
        selected = false
    )
}

@Composable
@Preview()
private fun BottomBarPreview() {
    HoroscopeBottomBar(
        listOf(Horoscope, Luck, Palmistry),
        currentScreen = Horoscope,
        onTabSelected = {})
}

