package com.veroanggra.pennymate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.pennymate.R
import com.veroanggra.pennymate.ui.theme.BlueDark

@Composable
fun RectButtonTextFilled(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    colorButton: Color,
    colorLabel: Color,
    padding: Dp,
    height: Dp
) {
    TextButton(
        onClick = onClick,
        modifier
            .fillMaxWidth()
            .padding(start = padding, end = padding)
            .clip(RoundedCornerShape(4.dp))
            .padding()
            .height(height)
            .background(color = colorButton)
    ) {
        Text(
            text = label, style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorLabel
            )
        )
    }
}

@Composable
fun RectButtonTextIconOutline(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    colorButton: Color,
    colorOutline: Color,
    colorLabel: Color,
    padding: Dp,
    height: Dp,
    icon: Int = R.drawable.ic_google
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(start = padding, end = padding)
            .clip(RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = colorOutline, shape = RoundedCornerShape(4.dp))
            .height(height)
            .background(color = colorButton)
            .clickable { onClick },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = modifier.padding(start = 16.dp)
                .size(24.dp)
        )
        Text(
            text = label, style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = colorLabel
            ),
            modifier = modifier.weight(1f), textAlign = TextAlign.Center
        )
    }
}