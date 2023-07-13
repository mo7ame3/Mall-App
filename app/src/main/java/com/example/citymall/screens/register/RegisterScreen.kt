package com.example.citymall.screens.register

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.citymall.components.CircleInductor
import com.example.citymall.components.LoginButton
import com.example.citymall.components.PasswordInput
import com.example.citymall.components.TextInput
import com.example.citymall.constant.Constant
import com.example.citymall.data.WrapperClass
import com.example.citymall.model.authentication.Authentication
import com.example.citymall.navigation.AllScreens
import com.example.citymall.sharedpreference.SharedPreference
import com.example.citymall.ui.theme.redColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(navController: NavController, registerViewModel: RegisterViewModel) {
    var loading by remember {
        mutableStateOf(false)
    }
    val email = remember {
        mutableStateOf("")
    }
    val emailError = remember {
        mutableStateOf(false)
    }
    val name = remember {
        mutableStateOf("")
    }
    val nameError = remember {
        mutableStateOf(false)
    }
    val phone = remember {
        mutableStateOf("")
    }
    val phoneError = remember {
        mutableStateOf(false)
    }
    val password = remember {
        mutableStateOf("")
    }
    val eye = remember {
        mutableStateOf(false)
    }
    val passwordConfirm = remember {
        mutableStateOf("")
    }
    val eyeConfirm = remember {
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
                    text = "Register Screen", style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(25.dp))
                TextInput(input = name, label = "Name", onAction = KeyboardActions {
                    keyboardController?.hide()
                }, error = nameError, keyboardType = KeyboardType.Text)
                Spacer(modifier = Modifier.height(15.dp))
                TextInput(input = phone, label = "Phone", onAction = KeyboardActions {
                    keyboardController?.hide()
                }, error = phoneError, keyboardType = KeyboardType.Number)
                Spacer(modifier = Modifier.height(15.dp))
                TextInput(input = email, label = "Email", onAction = KeyboardActions {
                    keyboardController?.hide()
                }, error = emailError)
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInput(
                    password = password,
                    eye = eye,
                    onButtonAction = { eye.value = !eye.value },
                    onAction = KeyboardActions {
                        keyboardController?.hide()
                    })
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInput(
                    password = passwordConfirm,
                    eye = eyeConfirm,
                    label = "Confirm Password",
                    onButtonAction = { eyeConfirm.value = !eyeConfirm.value },
                    onAction = KeyboardActions {
                        if (!emailError.value && !nameError.value && !phoneError.value && password.value == passwordConfirm.value) {
                            loading = true
                            scope.launch {
                                val response: WrapperClass<Authentication, Boolean, Exception> =
                                    registerViewModel.register(
                                        email = email.value,
                                        password = password.value,
                                        name = name.value,
                                        phone = phone.value,
                                        passwordConfirm = passwordConfirm.value
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
                                }
                                else if (response.e != null) {
                                    loading = false
                                    Toast.makeText(
                                        context,
                                        "خطأ في الانترنت",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else if (response.data?.status == "fail" || response.data?.status == "error") {
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
                LoginButton(label = "Register") {
                    if (!emailError.value && !nameError.value && !phoneError.value && password.value == passwordConfirm.value) {
                        loading = true
                        scope.launch {
                            val response: WrapperClass<Authentication, Boolean, Exception> =
                                registerViewModel.register(
                                    email = email.value,
                                    password = password.value,
                                    name = name.value,
                                    phone = phone.value,
                                    passwordConfirm = passwordConfirm.value
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
                Spacer(modifier = Modifier.height(25.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "have account ?")
                    TextButton(onClick = {
                        navController.navigate(AllScreens.LoginScreen.name) {
                            navController.popBackStack()
                            navController.popBackStack()
                            navController.popBackStack()
                        }
                    }) {
                        Text(text = "Login", color = redColor)
                    }
                }

            }
        } else {
            CircleInductor()
        }
    }

}