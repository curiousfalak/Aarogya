package com.example.aarogya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aarogya.dashboard.WearableDashboard
import com.example.aarogya.ui.theme.AarogyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AarogyaTheme {

                }
            }
        }
    }




