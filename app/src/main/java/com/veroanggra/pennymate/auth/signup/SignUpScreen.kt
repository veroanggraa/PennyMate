package com.veroanggra.pennymate.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.veroanggra.pennymate.R
import com.veroanggra.pennymate.component.CustomTextField
import com.veroanggra.pennymate.component.RectButtonTextFilled
import com.veroanggra.pennymate.ui.theme.Black3C4E57
import com.veroanggra.pennymate.ui.theme.BlueDark
import com.veroanggra.pennymate.ui.theme.GrayB6B7B8

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = modifier.height(120.dp))
        Image(
            painter = painterResource(R.drawable.ic_penny_text),
            contentDescription = null,
            modifier = modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = modifier.height(40.dp))
        Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            color = Black3C4E57,
            text = "Sign Up & Let's Get Started!",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Text(
            modifier = modifier.align(Alignment.CenterHorizontally),
            text = "Sign up to PennyMate",
            color = GrayB6B7B8,
            fontSize = 13.sp
        )
        Spacer(modifier = modifier.height(42.dp))
        CustomTextField(
            modifier = modifier.align(Alignment.CenterHorizontally),
            padding = 40.dp,
            value = "",
            hint = "Name",
            hintColor = GrayB6B7B8,
            onValueChange = {},
            leadingIcon = painterResource(R.drawable.ic_user)
        )
        Spacer(modifier = modifier.height(30.dp))
        CustomTextField(
            modifier = modifier.align(Alignment.CenterHorizontally),
            padding = 40.dp,
            value = "",
            hint = "Email",
            hintColor = GrayB6B7B8,
            onValueChange = {},
            leadingIcon = painterResource(R.drawable.ic_mail)
        )
        Spacer(modifier = modifier.height(30.dp))
        CustomTextField(
            modifier = modifier.align(Alignment.CenterHorizontally),
            padding = 40.dp,
            value = "",
            hint = "Password",
            hintColor = GrayB6B7B8,
            onValueChange = {},
            leadingIcon = painterResource(R.drawable.ic_password),
            trailingIcon = painterResource(R.drawable.ic_eyes_closed)
        )
        Spacer(modifier = modifier.height(30.dp))
        CustomTextField(
            modifier = modifier.align(Alignment.CenterHorizontally),
            padding = 40.dp,
            value = "",
            hint = "Re-type password",
            hintColor = GrayB6B7B8,
            onValueChange = {},
            leadingIcon = painterResource(R.drawable.ic_password),
            trailingIcon = painterResource(R.drawable.ic_eyes_closed)
        )
        Spacer(modifier = modifier.height(40.dp))
        RectButtonTextFilled(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = {},
            label = "Confirm",
            colorButton = BlueDark,
            colorLabel = Color.White,
            padding = 40.dp,
            height = 48.dp
        )
        Spacer(modifier = modifier.weight(1f))
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                modifier = modifier,
                text = "Already have account?",
                color = GrayB6B7B8
            )
            Spacer(modifier = modifier.size(10.dp))
            Text(
                modifier = modifier.clickable {
                    navController.navigate("signin")
                },
                text = "Sign In",
                color = BlueDark,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = modifier.height(40.dp))
    }
}