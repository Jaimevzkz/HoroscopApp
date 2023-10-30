package com.example.horoscapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Waves
import androidx.compose.ui.graphics.vector.ImageVector

interface HoroscHomeDestinations {
    val icon: ImageVector
    val route: String
}
object Horoscope: HoroscHomeDestinations {
    override val icon = Icons.Filled.Waves
    override val route = "Horoscope"

}
object Luck: HoroscHomeDestinations {
    override val icon = Icons.Filled.Style
    override val route = "Luck"
}
object Palmistry: HoroscHomeDestinations {
    override val icon = Icons.Filled.FrontHand
    override val route = "Palmistry"
}

// Screens to be displayed in the bottom bar
val horoscopeScreens = listOf(Horoscope, Luck, Palmistry)