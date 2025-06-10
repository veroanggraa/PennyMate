package com.veroanggra.pennymate.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@Composable
fun CustomAlert(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    onDismissRequest: () -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = {},
        title = { Text(title) },
        text = { Text(content) },
        confirmButton = {
            Button(

                onClick = {
                    openDialog.value = false
                }) {
                Text("Confirm Button")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Dismiss Button")
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    cameraPermissionState: PermissionState
) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()
            },
            title = { Text(text = "Camera Permission Required") },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(com.veroanggra.pennymate.R.string.txt_camera_permission_info_first))
                    Spacer(modifier = modifier.height(8.dp))
                }
            },
            confirmButton = {
                Button(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                    onDismissRequest()
                }) {
                    Text(text = "Grant Permission")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismissRequest()
                }) {
                    Text(text = "Not Now")
                }
            }
        )

}