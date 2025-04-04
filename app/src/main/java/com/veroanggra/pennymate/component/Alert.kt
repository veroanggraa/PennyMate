package com.veroanggra.pennymate.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun CustomAlert(modifier: Modifier = Modifier, title: String ,onDismissRequest: () -> Unit) {
    val openDialog = remember { mutableStateOf(false)  }
    AlertDialog(
        onDismissRequest = {},
        title = {},
        text = {},
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