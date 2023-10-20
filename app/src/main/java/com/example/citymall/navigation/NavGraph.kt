package com.example.citymall.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.citymall.screens.forgetPassword.ForgetPasswordScreen
import com.example.citymall.screens.forgetPassword.ForgetPasswordViewModel
import com.example.citymall.screens.forgetPassword.ResetPasswordScreen
import com.example.citymall.screens.home.HomeScreen
import com.example.citymall.screens.login.LoginScreen
import com.example.citymall.screens.login.LoginViewModel
import com.example.citymall.screens.register.RegisterScreen
import com.example.citymall.screens.register.RegisterViewModel
import com.example.citymall.screens.splash.SplashScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AllScreens.SplashScreen.name
    ) {

        composable(route = AllScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(route = AllScreens.LoginScreen.name) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }

        composable(route = AllScreens.RegisterScreen.name) {
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }

        composable(route = AllScreens.ForgetPasswordScreen.name) {
            val forgetPasswordViewModel = hiltViewModel<ForgetPasswordViewModel>()
            ForgetPasswordScreen(
                navController = navController,
                forgetPasswordViewModel = forgetPasswordViewModel
            )
        }
        composable(route = AllScreens.ResetPasswordScreen.name) {
            val forgetPasswordViewModel = hiltViewModel<ForgetPasswordViewModel>()
            ResetPasswordScreen(
                navController = navController,
                forgetPasswordViewModel = forgetPasswordViewModel
            )
        }

        composable(route = AllScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }


    }
}