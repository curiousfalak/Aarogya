package com.example.aarogya.postureCorrection

import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class PointF3(val x: Float, val y: Float, val z: Float)

class PoseViewModel : ViewModel() {
    var feedback by mutableStateOf("Waiting for input...")
        private set

    var landmarks by mutableStateOf<List<PointF3>>(emptyList())
        private set

    var mode = "beginner"

    var stateSeq: MutableList<String> = mutableListOf()
    var displayText: BooleanArray = BooleanArray(5)
    var countFrames: IntArray = IntArray(5)
    var lowerHips: Boolean = false
    var incorrectPosture: Boolean = false
    var prevState: String? = null
    var currState: String? = null

    var squatCount: Int = 0
    var improperSquat: Int = 0

    private var startInactiveTime: Long = SystemClock.elapsedRealtime()
    private var startInactiveTimeFront: Long = SystemClock.elapsedRealtime()
    var inactiveTime: Long = 0L
    var inactiveTimeFront: Long = 0L

    fun updateFeedback(text: String) {
        feedback = text
    }

    fun updateLandmarks(points: List<PointF3>) {
        landmarks = points
    }

    fun resetInactivity() {
        startInactiveTime = SystemClock.elapsedRealtime()
        inactiveTime = 0L
    }

    fun resetInactivityFront() {
        startInactiveTimeFront = SystemClock.elapsedRealtime()
        inactiveTimeFront = 0L
    }

    fun addInactiveDeltaFront() {
        val now = SystemClock.elapsedRealtime()
        inactiveTimeFront += (now - startInactiveTimeFront)
        startInactiveTimeFront = now
    }

    fun addInactiveDelta() {
        val now = SystemClock.elapsedRealtime()
        inactiveTime += (now - startInactiveTime)
        startInactiveTime = now
    }

    fun resetAllState() {
        stateSeq.clear()
        displayText = BooleanArray(5)
        countFrames = IntArray(5)
        lowerHips = false
        incorrectPosture = false
        prevState = null
        currState = null
        squatCount = 0
        improperSquat = 0
        resetInactivity()
        resetInactivityFront()
    }
}
