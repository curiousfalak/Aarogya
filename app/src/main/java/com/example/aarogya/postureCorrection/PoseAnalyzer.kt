package com.example.aarogya.postureCorrection

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.sqrt

class PoseAnalyzer(
    private val context: Context,
    private val mode: String,
    private val viewModel: PoseViewModel
) {
    companion object {
        private const val TAG = "PoseAnalyzer"
    }

    private val thresholds = if (mode.lowercase() == "pro") Thresholds.pro else Thresholds.beginner

    private val baseOptions = BaseOptions.builder()
        .setModelAssetPath("models/pose_landmarker_full.task")
        .build()

    private val options = PoseLandmarker.PoseLandmarkerOptions.builder()
        .setBaseOptions(baseOptions)
        .setRunningMode(RunningMode.LIVE_STREAM)
        .setResultListener { result, _ -> onResult(result) }
        .setErrorListener { e -> Log.e(TAG, "❌ MediaPipe error: ${e.message}", e) }
        .build()

    private val landmarker = PoseLandmarker.createFromOptions(context, options)

    fun analyze(bitmap: Bitmap) {
        try {
            val mpImage = BitmapImageBuilder(bitmap).build()
            landmarker.detectAsync(mpImage, System.currentTimeMillis())
        } catch (e: Exception) {
            Log.e(TAG, "❌ Error analyzing frame: ${e.message}", e)
        }
    }

    private fun onResult(result: PoseLandmarkerResult?) {
        if (result == null || result.landmarks().isEmpty()) {
            viewModel.updateFeedback("No person detected")
            return
        }

        val lms = result.landmarks()[0]
        val allPoints = lms.map { PointF3(it.x(), it.y(), it.z()) }
        viewModel.updateLandmarks(allPoints)

        val hip = lms[23]
        val knee = lms[25]
        val ankle = lms[27]
        val shoulder = lms[11]

        val hipAngle = calculateAngle(shoulder.x(), hip.x(), knee.x(), shoulder.y(), hip.y(), knee.y())
        val kneeAngle = calculateAngle(hip.x(), knee.x(), ankle.x(), hip.y(), knee.y(), ankle.y())

        Log.d(
            TAG, """
            🦵 Angles:
            | Hip angle: ${"%.1f".format(hipAngle)}°
            | Knee angle: ${"%.1f".format(kneeAngle)}°
        """.trimIndent()
        )

        val kneeThreshold = thresholds["KNEE_THRESH"] as FloatArray
        val hipThreshold = thresholds["HIP_THRESH"] as FloatArray

        val feedback = when {
            hipAngle < hipThreshold[0] -> "Bend forward — keep back straight"
            hipAngle > hipThreshold[1] -> "Leaning back — correct your spine"
            kneeAngle > kneeThreshold[2] -> "Squat too deep"
            kneeAngle < kneeThreshold[0] -> "Not enough squat"
            else -> "✅ Good posture"
        }

        Log.i(TAG, "📐 Feedback: $feedback")
        viewModel.updateFeedback(feedback)
    }

    private fun calculateAngle(
        x1: Float, x2: Float, x3: Float,
        y1: Float, y2: Float, y3: Float
    ): Float {
        val angle = Math.toDegrees(
            atan2((y3 - y2).toDouble(), (x3 - x2).toDouble()) -
                    atan2((y1 - y2).toDouble(), (x1 - x2).toDouble())
        )
        var ang = abs(angle)
        if (ang > 180) ang = 360 - ang
        return ang.toFloat()
    }
}
