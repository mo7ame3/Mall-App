package com.example.citymall.screens.login

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citymall.components.CircleInductor
import com.example.citymall.components.TextInput
import com.example.citymall.components.LoginButton
import com.example.citymall.components.PasswordInput
import com.example.citymall.constant.Constant
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.authentication.Authentication
import com.example.citymall.navigation.AllScreens
import com.example.citymall.sharedpreference.SharedPreference
import com.example.citymall.ui.theme.redColor
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    var loading by remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val emailError = remember {
        mutableStateOf(false)
    }
    val password = remember {
        mutableStateOf("")
    }
    val eye = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sharedPreference = SharedPreference(context)
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(modifier = Modifier.fillMaxSize()) {
        if (!loading) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login Screen", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(25.dp))
                TextInput(input = email, label = "Email", onAction = KeyboardActions {
                    keyboardController?.hide()
                }, error = emailError)
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInput(
                    password = password,
                    eye = eye,
                    onButtonAction = { eye.value = !eye.value },
                    onAction = KeyboardActions {
                        if (!emailError.value && password.value.isNotBlank()) {
                            loading = true
                            scope.launch {
                                val response: WrapperClass<Authentication, Boolean, Exception> =
                                    loginViewModel.login(
                                        email = email.value,
                                        password = password.value
                                    )
                                if (response.data?.status == "success") {
                                    loading = false
                                    sharedPreference.saveToken(token = response.data!!.token.toString())
                                    Constant.token = response.data!!.token.toString()
                                    navController.navigate(route = AllScreens.HomeScreen.name) {
                                        navController.popBackStack()
                                        navController.popBackStack()
                                        navController.popBackStack()
                                    }
                                } else if (response.e != null) {
                                    loading = false
                                    Toast.makeText(
                                        context,
                                        "خطأ في الانترنت",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (response.data?.status == "fail" || response.data?.status == "error") {
                                    loading = false
                                    Toast.makeText(
                                        context,
                                        response.data?.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                keyboardController?.hide()
                            }
                        }
                    })
                Spacer(modifier = Modifier.height(15.dp))
                LoginButton(label = "Login") {
                    if (!emailError.value && password.value.isNotBlank()) {
                        loading = true
                        scope.launch {
                            val response: WrapperClass<Authentication, Boolean, Exception> =
                                loginViewModel.login(
                                    email = email.value,
                                    password = password.value
                                )
                            if (response.data?.status == "success") {
                                loading = false
                                sharedPreference.saveToken(token = response.data!!.token.toString())
                                Constant.token = response.data!!.token.toString()
                                navController.navigate(route = AllScreens.HomeScreen.name) {
                                    navController.popBackStack()
                                    navController.popBackStack()
                                    navController.popBackStack()
                                }
                            } else if (response.e != null) {
                                loading = false
                                Toast.makeText(
                                    context,
                                    "خطأ في الانترنت",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (response.data?.status == "fail" || response.data?.status == "error") {
                                loading = false
                                Toast.makeText(
                                    context,
                                    response.data?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

                TextButton(onClick = {
                    navController.navigate(AllScreens.ForgetPasswordScreen.name)
                }) {
                    Text(text = "Forget Password ", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Don't have account yet ?")
                    TextButton(onClick = {
                        navController.navigate(AllScreens.RegisterScreen.name)
                    }) {
                        Text(text = "Register Now", color = redColor)
                    }
                }

            }
        } else {
            CircleInductor()
        }
    }
}