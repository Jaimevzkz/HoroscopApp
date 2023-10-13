package com.example.horoscapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.horoscapp.R
import com.example.horoscapp.ui.components.HoroscopeBottomBar
import com.example.horoscapp.ui.components.HoroscopeTopAppBar
import com.example.horoscapp.ui.detail.HoroscopeDetailScreen
import com.example.horoscapp.ui.horoscope.HoroscopeScreen
import com.example.horoscapp.ui.horoscope.HoroscopeViewModel
import com.example.horoscapp.ui.luck.LuckScreen
import com.example.horoscapp.ui.palmistry.PalmistryScreen
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.accent
import com.example.horoscapp.ui.theme.primary
import com.example.horoscapp.ui.theme.primaryDark
import com.example.horoscapp.ui.theme.red
import dagger.hilt.android.AndroidEntryPoint

val BACKGROUND_COLOR = primary

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            horoscopeApp()
        }
    }
}

@Composable
fun horoscopeApp() {
    HoroscappTheme {
        val navControllerAllNavHost = rememberNavController()
        HoroscopeAllNavHost(navControllerAllNavHost)
    }

}
@Composable
fun HoroscopeAllNavHost(navControllerAllNavHost: NavHostController){ //This is the root NavHost to navigate throw all child NavHosts
    NavHost(
        navController = navControllerAllNavHost,
        startDestination = Home.route
    ) {
        composable(route = Home.route) {
            val navControllerHomeNavHost = rememberNavController()
            HoroscopeNavHost(navControllerHomeNavHost){horoscopeModelArg ->
                navControllerAllNavHost.navigateToDetail(horoscopeModelArg)
            }
        }
        composable(
            route = Detail.routeWithArgs,
            arguments = Detail.arguments
        ) { navBackStackEntry -> //navBackStackEntry saves data for the actual route
            //retrieve argument
            val horoscopeModelArg =
                navBackStackEntry.arguments?.getString(Detail.horoscopeModelArg)
                    ?: "Aries"
            HoroscopeDetailScreen(horoscopeModelArg = horoscopeModelArg, backGroundColor = BACKGROUND_COLOR, onBackClicked = {
                navControllerAllNavHost.navigateSingleTopTo(Home.route)
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoroscopeNavHost(navControllerHomeNavHost: NavHostController, onHoroscopeSelected: (String) -> Unit) { //This is the NavHost of the home with bottom bar
    val currentBackStack by navControllerHomeNavHost.currentBackStackEntryAsState()
    // Fetch your currentDestination:
    val currentDestination = currentBackStack?.destination
    // Change the variable to this and use Overview as a backup screen if this returns null
    val currentScreen =
        horoscopeScreens.find { it.route == currentDestination?.route } ?: Horoscope
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = primaryDark),
                title = {
                    HoroscopeTopAppBar(
                        text = stringResource(R.string.app_name)
                    )
                },
            )

        },
        bottomBar = {
            BottomAppBar(
                containerColor = primaryDark,
                contentColor = accent,
            ) {
                HoroscopeBottomBar(
                    allScreens = horoscopeScreens,
                    onTabSelected = { newScreen ->
                        navControllerHomeNavHost.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navControllerHomeNavHost,
            startDestination = Horoscope.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Horoscope.route) {
                val horoscopeViewModel = hiltViewModel<HoroscopeViewModel>()
                HoroscopeScreen(
                    horoscopeViewModel = horoscopeViewModel,
                    backGroundColor = BACKGROUND_COLOR,
                    onItemSelected = { horoscopeModelArg ->
                        onHoroscopeSelected(horoscopeModelArg)
                    })
            }
            composable(route = Luck.route) {
                LuckScreen()
            }
            composable(route = Palmistry.route) {
                PalmistryScreen()
            }
        }
    }

}

private fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}

private fun NavHostController.navigateToDetail(horoscopeModelArg: String) {
    this.navigateSingleTopTo("${Detail.route}/$horoscopeModelArg")
}

@Preview
@Composable
fun horoscopePreview() {
    HoroscappTheme {
        horoscopeApp()
    }
}