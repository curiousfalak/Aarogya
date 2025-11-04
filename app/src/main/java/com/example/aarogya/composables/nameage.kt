// info.kt
package com.example.aarogya.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(onSubmit: (String, String, String, String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    // Reusable black theme for text fields


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Let's know you better!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

        )

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)", color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),

        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                onSubmit(username, age, gender, height, weight)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CE116))
        ) {
            Text("Continue", color = Color.White)
        }
    }
}
