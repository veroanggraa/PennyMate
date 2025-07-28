package com.veroanggra.pennymate.feature.main

import android.Manifest
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.veroanggra.pennymate.Screen
import com.veroanggra.pennymate.component.CameraPermissionDialog
import com.veroanggra.pennymate.component.ScanLineAnimation
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.util.concurrent.Executors


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplitBillMainScreen(modifier: Modifier = Modifier, navController: NavController) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var showCameraPermissionDialog by remember { mutableStateOf(false) }
    var hasNavigated by remember { mutableStateOf(false) }
    var scannerBillText by remember { mutableStateOf("") }

    LaunchedEffect(cameraPermissionState.hasPermission) {
        if (!cameraPermissionState.hasPermission) {
            showCameraPermissionDialog = true
        } else {
            showCameraPermissionDialog = false
        }
    }

    if (showCameraPermissionDialog) {
        CameraPermissionDialog(
            onDismissRequest = { showCameraPermissionDialog = false },
            cameraPermissionState = cameraPermissionState,
            modifier = Modifier
        )
    } else {
        if (cameraPermissionState.hasPermission) {
            CameraScanScreen(modifier = modifier, onBillScanned = { text ->
                if (text.isNotBlank() && !hasNavigated) {
                    val parsedBill = BillParser.parseScannedTextToBillDetails(text)
                    if (parsedBill != null && (parsedBill.items.isNotEmpty() || parsedBill.total.toDouble() > 0.0)) {
                        try {
                            val jsonString = Json.encodeToString(parsedBill)
                            hasNavigated = true
                            navController.navigate(Screen.SplitBillResultScreen.createRoute(jsonString))
                        } catch (e: Exception) {
                            Log.e("SplitBillMainScreen", "Error encoding or navigating", e)
                        }
                    }
                }
                scannerBillText = text
                Log.d("SplitBillMainScreen", "Scanned Bill Text: $scannerBillText")
            })
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Camera permission denied, cannot scan bill")
                Button(onClick = { showCameraPermissionDialog = true }) {
                    Text(text = "Grant Permission")
                }
            }
        }
    }
}

@Composable
fun CameraScanScreen(modifier: Modifier = Modifier, onBillScanned: (String) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val textRecognizer =
        remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }
    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                val cameraProvider = cameraProviderFuture.get()
                val preview = androidx.camera.core.Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build().also {
                        it.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
                            processImageProxy(textRecognizer, imageProxy, onBillScanned)
                        }
                    }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, preview, imageAnalyzer
                    )
                } catch (e: Exception) {
                    Log.e("CameraX", "Use case binding failed, e")
                }
                previewView
            }
        )
        ScanLineAnimation(modifier = modifier.fillMaxSize())
    }
}

@androidx.annotation.OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    textRecognizer: TextRecognizer,
    imageProxy: ImageProxy,
    onBillScanned: (String) -> Unit
) {
    imageProxy.image?.let { mediaImage ->
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        textRecognizer.process(image)
            .addOnSuccessListener { visionText ->
                val detectedText = visionText.text
                if (detectedText.isNotBlank()) {
                    Log.d("Bill Scanner", "Detected text : $detectedText")
                    onBillScanned(detectedText)
                } else {
                    Log.d("Bill Scanner", "No meaningful text detected in this frame.")

                }
                imageProxy.close()
            }
            .addOnFailureListener { e ->
                Log.e("Bill Scanner", "Text recognation", e)
                imageProxy.close()
            }
    } ?: run { imageProxy.close() }
}
