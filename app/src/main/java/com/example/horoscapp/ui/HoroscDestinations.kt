package com.example.horoscapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.FrontHand
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Waves
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.horoscapp.domain.model.HoroscopeModel

interface HoroscDestinations {
    val icon: ImageVector
    val route: String
}
object Horoscope: HoroscDestinations {
    override val icon = Icons.Filled.Waves
    override val route = "Horoscope"

}
object Luck: HoroscDestinations {
    override val icon = Icons.Filled.Style
    override val route = "Luck"
}
object Palmistry: HoroscDestinations {
    override val icon = Icons.Filled.FrontHand
    override val route = "Palmistry"
}
object Detail: HoroscDestinations {
    override val icon = Icons.Filled.Details
    override val route = "Detail"
    const val horoscopeModelArg = "horoscopeModelArg"
    val routeWithArgs = "${route}/{${horoscopeModelArg}}"
    //Arguments passed to the screen
    val arguments = listOf(navArgument(horoscopeModelArg) { type = NavType.StringType })

}


// Screens to be displayed in the bottom bar
val horoscopeScreens = listOf(Horoscope, Luck, Palmistry)