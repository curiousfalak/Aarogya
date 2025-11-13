package com.example.aarogya.postureCorrection

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun CameraPreview(poseViewModel: PoseViewModel) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val poseAnalyzer = remember {
        PoseAnalyzer(context, poseViewModel.mode, poseViewModel)
    }

    val previewView = remember { PreviewView(context) }

    Box(modifier = Modifier.fillMaxSize()) {

        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize()) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(ContextCompat.getMainExecutor(context)) { imageProxy ->
                            val bitmap = imageProxy.toBitmap()
                            if (bitmap != null) {
                                poseAnalyzer.analyze(bitmap)
                                Log.d(
                                    "CameraPreview",
                                    "📷 Frame received from camera: ${imageProxy.width}x${imageProxy.height}"
                                )
                            }
                            imageProxy.close()
                        }
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_FRONT_CAMERA,
                        preview,
                        imageAnalyzer
                    )
                } catch (exc: Exception) {
                    Log.e("CameraPreview", "❌ Camera binding failed: ${exc.message}")
                }
            }, ContextCompat.getMainExecutor(context))
        }

//        PoseOverlay(poseViewModel.landmarks)

        PoseOverlay(poseViewModel)

        Text(
            text = poseViewModel.feedback,
            color = Color.Black,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
