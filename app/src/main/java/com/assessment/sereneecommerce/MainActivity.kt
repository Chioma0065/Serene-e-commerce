package com.assessment.sereneecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.assessment.sereneecommerce.navigation.Screen
import com.assessment.sereneecommerce.ui.detail.DetailScreen
import com.assessment.sereneecommerce.ui.home.HomeScreen
import com.assessment.sereneecommerce.ui.theme.SereneBottomNavBar
import com.assessment.sereneecommerce.ui.theme.SereneCommerceTheme
import com.assessment.sereneecommerce.ui.theme.SereneTopBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SereneCommerceTheme {
                SereneCommerceApp()
            }
        }
    }
}

@Composable
fun SereneCommerceApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        topBar = {
            SereneTopBar(
                title = if (currentDestination?.route == Screen.Detail.route) "Produce Detail" else "Shop Explore",
                shouldShowNavIcon = currentDestination?.route == Screen.Detail.route,
                onNavIconClick = { navController.popBackStack() }
            )
        },
        bottomBar = {
            SereneBottomNavBar(
                currentDestination = currentDestination,
                navController = navController
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Cart.route) { PlaceholderScreen("Cart") }
            composable(Screen.Favorites.route) { PlaceholderScreen("Favorites") }
            composable(Screen.Profile.route) { PlaceholderScreen("Profile") }
            composable(Screen.Detail.route) { DetailScreen() }
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "$name Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SereneCommerceTheme {
        SereneCommerceApp()
    }
}
