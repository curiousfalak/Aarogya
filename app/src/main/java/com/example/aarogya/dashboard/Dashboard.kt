@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.aarogya.dashboard

import co.yml.charts.ui.linechart.model.Line
import coil3.compose.AsyncImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import com.example.aarogya.R

@Composable
fun WearableDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Wearable Integration",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { /* Notifications */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Notifications"
                    )
                }
                AsyncImage(
                    model = "https://randomuser.me/api/portraits/women/65.jpg",
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Top Metrics Grid
        MetricsGrid()

        Spacer(modifier = Modifier.height(24.dp))

        // Charts Section
        StepsTrendChart()
        Spacer(modifier = Modifier.height(24.dp))
        CaloriesBurnedChart()
    }
}

@Composable
fun MetricsGrid() {
    Column {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricCard(
                title = "Steps",
                value = "9,850",
                unit = "steps",
                progress = 9850f / 10000f,
                goal = "Goal: 10,000 steps",
                color = Color(0xFF4CAF50)
            )
            MetricCard(
                title = "Calories Burned",
                value = "480",
                unit = "kcal",
                progress = 480f / 600f,
                goal = "Goal: 600 kcal",
                color = Color(0xFF2196F3)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MetricSmallCard("Avg. Heart Rate", "72", "bpm", Color(0xFFFF6D6D))
            MetricSmallCard("Distance", "6.5", "km", Color(0xFF673AB7))
        }
    }
}

@Composable
fun MetricCard(title: String, value: String, unit: String, progress: Float, goal: String, color: Color) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .width(170.dp)
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier
            .background(Color.White)
            .padding(12.dp)
            ) {
            Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("$value", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(unit, fontSize = 12.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(progress = progress, color = color, modifier = Modifier.fillMaxWidth())
            Text(goal, fontSize = 11.sp, color = Color.Gray, textAlign = TextAlign.Start)
        }
    }
}

@Composable
fun MetricSmallCard(title: String, value: String, unit: String, color: Color) {
    Card(
        modifier = Modifier
            .background(Color.White)
            .width(170.dp)
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, color = color, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(value, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text(unit, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Composable
fun StepsTrendChart() {
    val points = listOf(
        Point(0f, 9000f),
        Point(1f, 7500f),
        Point(2f, 8200f),
        Point(3f, 10500f),
        Point(4f, 11800f),
        Point(5f, 9700f),
        Point(6f, 8800f)
    )

    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    lineStyle = LineStyle(color = Color(0xFF4CAF50))
                )
            )
        ),
        xAxisData = AxisData.Builder().axisStepSize(40.dp)
            .labelData { i -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")[i] }
            .build(),
        yAxisData = AxisData.Builder().steps(5).build()
    )

    ChartCard("Daily Steps Trend", "You've maintained a strong step count this week, exceeding your goal on most days.") {
        LineChart(modifier = Modifier.height(200.dp), lineChartData = data)
    }
}

@Composable
fun CaloriesBurnedChart() {
    val points = listOf(
        Point(0f, 400f),
        Point(1f, 320f),
        Point(2f, 450f),
        Point(3f, 480f),
        Point(4f, 560f),
        Point(5f, 590f),
        Point(6f, 470f)
    )

    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = points,
                    lineStyle = LineStyle(color = Color(0xFF2196F3))
                )
            )
        ),
        xAxisData = AxisData.Builder().axisStepSize(40.dp)
            .labelData { i -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")[i] }
            .build(),
        yAxisData = AxisData.Builder().steps(5).build()
    )

    ChartCard("Calories Burned Overview", "Your calorie burn has been consistent, showing good effort in your workouts.") {
        LineChart(modifier = Modifier.height(200.dp), lineChartData = data)
    }
}

@Composable
fun ChartCard(title: String, subtitle: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            content()
            Spacer(modifier = Modifier.height(8.dp))
            Text(subtitle, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    WearableDashboard()
}