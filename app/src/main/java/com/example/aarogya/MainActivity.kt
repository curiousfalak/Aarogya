package com.example.aarogya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.aarogya.composables.CommunityAndSupport
import com.example.aarogya.composables.LeaderboardScreen
import com.example.aarogya.composables.LoginScreen
import com.example.aarogya.composables.MainViewModel
import com.example.aarogya.composables.SplashScreen
import com.example.aarogya.dashboard.WearableDashboard
import com.example.aarogya.ui.theme.AarogyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AarogyaTheme{
                Spacer(modifier = Modifier.height(1000.dp))

                }
            }
        }
    }




