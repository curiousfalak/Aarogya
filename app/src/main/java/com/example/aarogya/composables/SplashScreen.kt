package com.example.aarogya.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.aarogya.R

class MainViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(600)
            _isLoading.value = false
        }
    }
}

@Composable
fun SplashScreen(navController: NavController, mainViewModel: MainViewModel = viewModel()) {
    val isLoading by mainViewModel.isLoading.collectAsState()

//        LaunchedEffect(key1 = isLoading) {
//            if (!isLoading) {
//                navController.navigate(Screen.Login.route) {
//                    popUpTo("splash") { inclusive = true }
//                }
//            }
//    }
//        LaunchedEffect(key1 = isLoading) {
//            if (!isLoading) {
//                navController.navigate(Screen.NavigationDrawer.route) {
//                    popUpTo("splash") { inclusive = true }
//                }
//            }
//        }

    Surface(
        modifier = Modifier.fillMaxSize().background(color = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Aarogya",
                fontSize = 32.sp,
                fontWeight = FontWeight.W400,
                )

            Spacer(Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Dumbbells",
                modifier = Modifier.size(112.dp).scale(2f,2f)
                    .clip(shape = RoundedCornerShape(45.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController = rememberNavController(), mainViewModel = MainViewModel())
}