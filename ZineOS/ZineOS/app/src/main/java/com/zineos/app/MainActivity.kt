package com.zineos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zineos.app.data.ZineViewModel
import com.zineos.app.ui.screens.FeedScreen
import com.zineos.app.ui.screens.PreferencesScreen
import com.zineos.app.ui.screens.ZineDestination
import com.zineos.app.ui.theme.ZineOSTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ZineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen BEFORE calling super.onCreate
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val selectedTheme by viewModel.selectedTheme.collectAsState()
            val navController = rememberNavController()

            ZineOSTheme(theme = selectedTheme) {
                NavHost(
                    navController = navController,
                    startDestination = ZineDestination.Feed.route
                ) {
                    composable(route = ZineDestination.Feed.route) {
                        FeedScreen(
                            onNavigateToPreferences = {
                                navController.navigate(ZineDestination.Preferences.route)
                            }
                        )
                    }
                    composable(route = ZineDestination.Preferences.route) {
                        PreferencesScreen(
                            currentTheme = selectedTheme,
                            onThemeSelected = { theme ->
                                viewModel.setTheme(theme)
                            },
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
