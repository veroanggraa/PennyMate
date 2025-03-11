package com.veroanggra.pennymate.auth.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.veroanggra.pennymate.R
import com.veroanggra.pennymate.component.RectButtonTextFilled
import com.veroanggra.pennymate.component.RectButtonTextIconOutline
import com.veroanggra.pennymate.ui.theme.Black3C4E57
import com.veroanggra.pennymate.ui.theme.BlueDark
import com.veroanggra.pennymate.ui.theme.GrayB6B7B8

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = modifier.height(180.dp))
        Image(
            painter = painterResource(R.drawable.ic_penny_text),
            contentDescription = null,
            modifier = modifier
                .size(203.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = modifier.height(40.dp))
        RectButtonTextFilled(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = {},
            label = "Sign In",
            colorButton = BlueDark,
            colorLabel = Color.White
        )
        Spacer(modifier = modifier.height(16.dp))
        RectButtonTextIconOutline(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = {},
            label = "Sign in with Google",
            colorButton = Color.White,
            colorOutline = GrayB6B7B8,
            colorLabel = GrayB6B7B8
        )
        Spacer(modifier = modifier.weight(1f))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don’t have account?", color = Black3C4E57)
            Spacer(modifier = modifier.width(10.dp))
            Text(
                text = "Sign Up",
                color = BlueDark,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = modifier.height(45.dp))
    }
}