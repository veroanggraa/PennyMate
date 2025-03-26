package com.veroanggra.pennymate.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.veroanggra.pennymate.ui.theme.GrayB6B7B8

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Painter? = null,
    onTrailingIconClick: () -> Unit = {},
    trailingIcon: Painter? = null,
    hint: String,
    hintColor: Color,
    padding: Dp
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = padding, end = padding),
        colors = OutlinedTextFieldDefaults.colors(GrayB6B7B8),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            if (leadingIcon != null)
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = leadingIcon, contentDescription = null
                )
        },
        trailingIcon = {
            if (trailingIcon != null)
                IconButton(onClick = onTrailingIconClick) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = trailingIcon, contentDescription = null
                    )
                }
        },
        placeholder = {
            Text(text = hint, color = hintColor)
        }
    )
}