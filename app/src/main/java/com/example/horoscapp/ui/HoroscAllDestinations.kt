package com.example.horoscapp.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface HoroscAllDestinations {
    val route: String
}
object Home: HoroscAllDestinations {
    override val route = "Home"
}

object Detail: HoroscAllDestinations {
    override val route = "Detail"
    const val horoscopeModelArg = "horoscopeModelArg"
    val routeWithArgs = "${route}/{${horoscopeModelArg}}"
    //Arguments passed to the screen
    val arguments = listOf(navArgument(horoscopeModelArg) { type = NavType.StringType })
}