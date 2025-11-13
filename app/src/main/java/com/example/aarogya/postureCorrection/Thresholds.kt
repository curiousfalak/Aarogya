package com.example.aarogya.postureCorrection

object Thresholds {
    val beginner = mapOf(
        "HIP_KNEE_VERT_NORMAL" to (0f to 32f),
        "HIP_KNEE_VERT_TRANS" to (35f to 65f),
        "HIP_KNEE_VERT_PASS" to (70f to 95f),
        "HIP_THRESH" to floatArrayOf(10f, 50f),
        "ANKLE_THRESH" to 45f,
        "KNEE_THRESH" to floatArrayOf(50f, 70f, 95f),
        "OFFSET_THRESH" to 35f,
        "INACTIVE_THRESH" to 15f,
        "CNT_FRAME_THRESH" to 50f
    )

    val pro = mapOf(
        "HIP_KNEE_VERT_NORMAL" to (0f to 32f),
        "HIP_KNEE_VERT_TRANS" to (35f to 65f),
        "HIP_KNEE_VERT_PASS" to (80f to 95f),
        "HIP_THRESH" to floatArrayOf(15f, 50f),
        "ANKLE_THRESH" to 30f,
        "KNEE_THRESH" to floatArrayOf(50f, 80f, 95f),
        "OFFSET_THRESH" to 35f,
        "INACTIVE_THRESH" to 15f,
        "CNT_FRAME_THRESH" to 50f
    )
}
