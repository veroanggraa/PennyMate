package com.veroanggra.pennymate.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomAlert(modifier: Modifier = Modifier, onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
        },
        title = {
        },
        text = {
        },
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