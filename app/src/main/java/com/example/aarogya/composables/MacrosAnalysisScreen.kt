package com.example.aarogya.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController

@Composable
fun MacrosAnalysisScreen(navController: NavController) {
    val backgroundColor = Color(0xFFF8F9FA)
    val accentGreen = Color(0xFF00C853)
    val accentBlue = Color(0xFF2962FF)
    val accentRed = Color(0xFFFF5252)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
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
                        val sweepAngles = listOf(120f, 150f, 90f)
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
                    NutrientRow("Total Calories Consumed", "1850 kcal", accentGreen)
                    NutrientRow("Calorie Goal", "2000 kcal")
                    NutrientRow("Carbohydrates", "225g / 250g")
                    NutrientRow("Proteins", "150g / 180g")
                    NutrientRow("Fats", "62.5g / 70g")
                    NutrientRow("Remaining Calories", "150 kcal", accentBlue)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
//                    .background(Color(0xFFE8F5E9)),
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
                    Text(
                        "Log Your Meal",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
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


