package com.veroanggra.pennymate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veroanggra.pennymate.auth.main.AuthScreen
import com.veroanggra.pennymate.auth.signup.SignUpScreen

sealed class Screen(val route: String) {
    data object MainAuth : Screen("mainauth")
    data object SignUp : Screen("signup")
    data object Home : Screen("home")
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainAuth.route) {
        composable(Screen.MainAuth.route) { AuthScreen(modifier, navController) }
        composable(Screen.SignUp.route) { SignUpScreen(modifier, navController) }
    }
}