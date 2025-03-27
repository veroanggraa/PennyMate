package com.veroanggra.pennymate.feature.main

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.PermissionRequired


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplitBillMainScreen(modifier: Modifier = Modifier) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    PermissionRequired() { }
}