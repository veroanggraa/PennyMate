package com.veroanggra.pennymate.feature.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.veroanggra.pennymate.R
import com.veroanggra.pennymate.component.RectButtonTextFilled
import com.veroanggra.pennymate.ui.theme.Black3C4E57
import com.veroanggra.pennymate.ui.theme.BlueDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitBillLandingScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = modifier.background(Color.White),
                title = {
                    Text(
                        text = "Split Bill",
                        fontSize = 17.sp,
                        modifier = modifier.padding(top = 40.dp)
                    )
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        content = { innerPadding ->
            Column(modifier = modifier.fillMaxSize()) {
                Spacer(modifier = modifier.weight(1f))
                Image(
                    modifier = modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.ic_illustration_scan),
                    contentDescription = null
                )
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    text = "You have no active bill.\nCreate a new one by scanning or \nimportant bill photo from your album.",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Black3C4E57
                )
                Spacer(modifier = modifier.height(40.dp))
                RectButtonTextFilled(
                    modifier = modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        navController.navigate("split_bill_main")
                    },
                    label = "Add Bill",
                    colorButton = BlueDark,
                    colorLabel = Color.White,
                    padding = 120.dp,
                    height = 48.dp
                )
                Spacer(modifier = modifier.weight(1f))
                Text(
                    modifier = modifier
                        .clickable { }
                        .align(Alignment.CenterHorizontally),
                    text = "Log Out",
                    color = BlueDark,
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = modifier.height(40.dp))
            }
        })
}