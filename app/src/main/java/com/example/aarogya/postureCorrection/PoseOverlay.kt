//package com.example.aarogya.postureCorrection
//
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.drawscope.Stroke
//
//@Composable
//fun PoseOverlay(landmarks: List<PointF3>) {
//    Canvas(modifier = Modifier.fillMaxSize()) {
//        landmarks.forEach { point ->
//            drawCircle(
//                color = Color.Cyan,
//                radius = 8f,
//                center = Offset(
//                    point.x * size.width,
//                    point.y * size.height
//                ),
//                style = Stroke(width = 4f)
//            )
//        }
//    }
//}


package com.example.aarogya.postureCorrection

import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import kotlin.math.acos
import kotlin.math.abs
import kotlin.math.sqrt

@Composable
fun PoseOverlay(viewModel: PoseViewModel, modifier: Modifier = Modifier.fillMaxSize()) {
    val landmarks = viewModel.landmarks
    val flipX = true

    Canvas(modifier = modifier) {
        if (landmarks.isEmpty()) return@Canvas

        val w = size.width
        val h = size.height

        fun px(lm: PointF3): Float = if (flipX) w - (lm.x * w) else lm.x * w
        fun py(lm: PointF3): Float = lm.y * h

        val jointIndices = listOf(11, 12, 23, 24, 25, 26, 27, 28)

        val connections = listOf(
            11 to 23, 12 to 24, 23 to 25, 24 to 26, 25 to 27, 26 to 28
        )

        connections.forEach { (startIdx, endIdx) ->
            val s = landmarks.getOrNull(startIdx)
            val e = landmarks.getOrNull(endIdx)
            if (s != null && e != null) {
                drawLine(
                    color = Color.Cyan,
                    start = Offset(px(s), py(s)),
                    end = Offset(px(e), py(e)),
                    strokeWidth = 5f
                )
            }
        }

        jointIndices.forEach { idx ->
            landmarks.getOrNull(idx)?.let { lm ->
                drawCircle(
                    color = Color.Yellow,
                    radius = 7f,
                    center = Offset(px(lm), py(lm))
                )
            }
        }


    //        drawContext.canvas.nativeCanvas.apply {
//            drawText(
//                viewModel.feedback,
//                w / 2,
//                h * 0.1f,
//                android.graphics.Paint().apply {
//                    color = android.graphics.Color.WHITE
//                    textAlign = Paint.Align.CENTER
//                    textSize = 80f
//                }
//            )
//        }
    }
}
