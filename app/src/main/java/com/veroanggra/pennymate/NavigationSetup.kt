package com.veroanggra.pennymate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veroanggra.pennymate.auth.main.AuthScreen
import com.veroanggra.pennymate.auth.signin.SignInScreen
import com.veroanggra.pennymate.auth.signup.SignUpScreen
import com.veroanggra.pennymate.feature.landing.SplitBillLandingScreen

sealed class Screen(val route: String) {
    data object MainAuth : Screen("mainauth")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
    data object SignIn : Screen("signin")
    data object SplitBillLandingScreen : Screen("split_bill_landing")
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplitBillLandingScreen.route) {
        composable(Screen.MainAuth.route) { AuthScreen(modifier, navController) }
        composable(Screen.SignUp.route) { SignUpScreen(modifier, navController) }
        composable(Screen.SignIn.route) { SignInScreen(modifier, navController) }
        composable(Screen.SplitBillLandingScreen.route) { SplitBillLandingScreen(modifier, navController) }
    }
}