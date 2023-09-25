package com.example.horoscapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.horoscapp.ui.components.HoroscopeBottomBar
import com.example.horoscapp.ui.components.HoroscopeTopAppBar
import com.example.horoscapp.ui.horoscope.HoroscopeScreen
import com.example.horoscapp.ui.luck.LuckScreen
import com.example.horoscapp.ui.palmistry.PalmistryScreen
import com.example.horoscapp.ui.theme.HoroscappTheme
import com.example.horoscapp.ui.theme.accent
import com.example.horoscapp.ui.theme.primaryDark
import com.example.horoscapp.ui.theme.secondary

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            horoscopeApp()

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun horoscopeApp() {
    HoroscappTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
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
                        text = stringResource(R.string.app_name),
                        contentColor = secondary
                    )
                }, )

            },
            bottomBar = {
                BottomAppBar(
                    containerColor = primaryDark,
                    contentColor = accent,
                ) {
                    HoroscopeBottomBar(
                        allScreens = horoscopeScreens,
                        onTabSelected = { newScreen ->
                            navController.navigateSingleTopTo(newScreen.route)
                        },
                        currentScreen = currentScreen
                    )
                }
            }
        ) { innerPadding ->
            horoscopeNavHost(navController, innerPadding)
        }
    }

}

@Composable
fun horoscopeNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Horoscope.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = Horoscope.route) {
            HoroscopeScreen()
        }
        composable(route = Luck.route) {
            LuckScreen()
        }
        composable(route = Palmistry.route) {
            PalmistryScreen()
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}

@Preview
@Composable
fun horoscopePreview() {
    HoroscappTheme {
        horoscopeApp()
    }
}