package com.example.aarogya.navigation


//import android.R.attr.contentDescription
//
//
//import android.content.Context
//import androidx.compose.foundation.layout.*
//
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//
//import androidx.navigation.compose.*
//import androidx.navigation.compose.rememberNavController
//import com.example.aarogya.dashboard.WearableDashboard
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Botsheet(context: Context) {
//    val navController = rememberNavController()
//    var isNotificationEnabled by remember { mutableStateOf(false) }
//
//    Scaffold(
//        topBar = {
//            val navBackStackEntry = navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry.value?.destination?.route
//
//            if (currentRoute in listOf("home", "sightinghistory")) {
//                TopAppBar(
//                    title = { Text("Aarogya", color = Color.Black) },
//                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF78C4E5)),
//                    actions = {
//                        IconButton(onClick = { isNotificationEnabled = !isNotificationEnabled }) {
//
//                        }
//                    }
//                )
//            }
//        },
//        bottomBar = {
//            val navBackStackEntry = navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry.value?.destination?.route
//
//            if (currentRoute in listOf("home", "sightinghistory")) {
//                BottomAppBar(containerColor = Color(0xFF78C4E5)) {
//                    val bottomBarItems = listOf(
//
//                    )
//
//                    bottomBarItems.forEach { (icon, description, route) ->
//                        IconButton(
//                            onClick = {
//                                navController.navigate(route) {
//                                    popUpTo("home") { inclusive = false }
//                                    launchSingleTop = true
//                                }
//                            },
//                            modifier = Modifier.weight(1f)
//                        ) {
//
//                        }
//                    }
//                }
//            }
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = "home",
//            modifier = Modifier.padding(innerPadding)
//        ) {
//
//            composable("home") { WearableDashboard() }
//
//        }
//
//    }
//}