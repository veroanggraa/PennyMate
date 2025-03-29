package com.veroanggra.pennymate.feature.main

import android.Manifest
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.PermissionRequired
import com.veroanggra.pennymate.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplitBillMainScreen(modifier: Modifier = Modifier) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    PermissionRequired(
        permissionState = cameraPermissionState,
        permissionNotGrantedContent = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.txt_camera_permission_info_first))
                Spacer(modifier = modifier.height(8.dp))
                Row {
                    Button(onClick = {
                        cameraPermissionState.launchPermissionRequest()
                    }) {
                        Text(text = "Grant Permission")
                    }
                }

            }
        },
        permissionNotAvailableContent = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.txt_camera_permission_info_second))
                Spacer(modifier = modifier.height(8.dp))
                Row {
                    Button(onClick = {
                    }) {
                        Text(text = "Open Settings")
                    }
                }
            }
        }
    ) {}
}