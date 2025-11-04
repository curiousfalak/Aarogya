package com.example.aarogya.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PieChartOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.aarogya.composables.CommunityAndLeaderboardScreen

import com.example.aarogya.composables.LoginScreen
import com.example.aarogya.composables.MacrosAnalysisScreen
import com.example.aarogya.composables.SplashScreen
import com.example.aarogya.dashboard.WearableDashboard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AarogyaNavigation(context: Context) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            val currentRoute = currentRoute(navController)
            if (currentRoute in listOf("dashboard", "community","macro")) {
                TopAppBar(
                    title = { Text("Aarogya", color = Color.Black) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4CE116))
                )
            }
        },
        bottomBar = {
            val currentRoute = currentRoute(navController)
            if (currentRoute in listOf("dashboard", "community","macro")) {
                BottomAppBar(containerColor = Color(0xFF4CE116)) {
                    val items = listOf(
                        BottomNavItem("dashboard", Icons.Default.Home, "Home"),
                        BottomNavItem("community", Icons.Default.Group, "Community"),
                        BottomNavItem("macro", Icons.Default.PieChartOutline,"Macro")
                    )
                    items.forEach { item ->
                        IconButton(
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo("dashboard") { inclusive = false }
                                    launchSingleTop = true
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.description,
                                tint = if (currentRoute == item.route) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash") { SplashScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("dashboard") { WearableDashboard(navController) }
            composable("community") {
                CommunityAndLeaderboardScreen(navController)
            }
            composable("macro"){
                MacrosAnalysisScreen(navController)
            }

        }
    }
}

// Helper to get current route
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

data class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val description: String)
