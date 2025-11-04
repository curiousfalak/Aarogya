package com.example.aarogya.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.math.roundToInt

@Composable
fun MacrosAnalysisScreen(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F9FA)
    val accentGreen = Color(0xFF00C853)
    val accentBlue = Color(0xFF2962FF)
    val accentRed = Color(0xFFFF5252)

    val age = 22
    val gender = "female"
    val heightCm = 162
    val weightKg = 55
    val activityLevel = "moderate"
    val goal = "muscle_gain"

    val s = if (gender == "male") 5 else -161
    val bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) + s

    val activityFactor = when (activityLevel.lowercase()) {
        "sedentary" -> 1.2
        "light" -> 1.375
        "moderate" -> 1.55
        "active" -> 1.725
        "very_active" -> 1.9
        else -> 1.55
    }

    val tdee = bmr * activityFactor

    val adjustedCalories = when (goal.lowercase()) {
        "muscle_gain" -> tdee + 300
        "fat_loss" -> tdee - 300
        else -> tdee
    }

    val (proteinRatio, carbRatio, fatRatio) = when (goal.lowercase()) {
        "muscle_gain" -> Triple(0.30, 0.50, 0.20)
        "fat_loss" -> Triple(0.40, 0.40, 0.20)
        else -> Triple(0.30, 0.45, 0.25)
    }

    val proteinG = (adjustedCalories * proteinRatio / 4).roundToInt()
    val carbsG = (adjustedCalories * carbRatio / 4).roundToInt()
    val fatsG = (adjustedCalories * fatRatio / 9).roundToInt()

    val recommendation =
        when (goal.lowercase()) {
            "muscle_gain" -> "Increase protein intake by 15g and reduce fat by 10g for optimal muscle recovery."
            "fat_loss" -> "Stay in slight deficit, keep protein high for muscle preservation."
            else -> "Maintain balanced intake to support daily energy needs."
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Daily Macro Breakdown", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.height(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF1F3F4),
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Today")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Canvas(modifier = Modifier.size(180.dp)) {
                    val total = proteinG + carbsG + fatsG
                    val sweepAngles = listOf(
                        (proteinG / total.toFloat()) * 360f,
                        (carbsG / total.toFloat()) * 360f,
                        (fatsG / total.toFloat()) * 360f
                    )
                    val colors = listOf(accentGreen, accentBlue, accentRed)
                    var startAngle = -90f
                    for (i in sweepAngles.indices) {
                        drawArc(
                            color = colors[i],
                            startAngle = startAngle,
                            sweepAngle = sweepAngles[i],
                            useCenter = false,
                            style = Stroke(width = 30f, cap = StrokeCap.Round)
                        )
                        startAngle += sweepAngles[i]
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Nutrient Summary", fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(12.dp))
                NutrientRow("BMR", "${bmr.roundToInt()} kcal")
                NutrientRow("TDEE", "${tdee.roundToInt()} kcal")
                NutrientRow("Adjusted Calories", "${adjustedCalories.roundToInt()} kcal", Color.Black)
                NutrientRow("Protein", "${proteinG} g", accentGreen)
                NutrientRow("Carbohydrates", "${carbsG} g", accentBlue)
                NutrientRow("Fats", "${fatsG} g", accentRed)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFCAFDDF))
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("🍽️", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Log Your Meal", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    "Get instant AI-driven macro analysis for balanced nutrition.",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentGreen)
                ) {
                    Text("Analyze Meal", color = Color.White)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(recommendation, textAlign = TextAlign.Center, fontSize = 13.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun NutrientRow(label: String, value: String, valueColor: Color = Color.Black) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.DarkGray, fontSize = 14.sp)
        Text(value, color = valueColor, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

