package com.example.citymall.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.citymall.R
import com.example.citymall.ui.theme.GreyDark
import com.example.citymall.ui.theme.GreyLight
import com.example.citymall.ui.theme.grayColor
import com.example.citymall.ui.theme.mainColor
import com.example.citymall.ui.theme.redColor
import com.example.citymall.ui.theme.whiteColor

@Composable
fun CircleInductor() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = redColor)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    keyboardType: KeyboardType = KeyboardType.Email,
    error: MutableState<Boolean> = mutableStateOf(false),
    label: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    isSingleLine: Boolean = true,
) {
    val emailRegex = "^[A-Za-z](.*)(@)(.+)(\\.)(.+)"
    val phoneRegex = "1[0-9](.+)"
    val nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        shape = RoundedCornerShape(25.dp),
        label = { Text(text = label) },
        value = input.value,
        onValueChange = {
            if (label == "Email") {
                input.value = it
                error.value = !emailRegex.toRegex().matches(it)
            } else if (label == "Phone") {
                error.value = !phoneRegex.toRegex().matches(it)
                if (it.length <= 10) {
                    input.value = it
                }
            } else if (label == "Name") {
                input.value = it
                error.value = !nameRegex.toRegex().matches(it)
            } else {
                input.value = it
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = onAction,
        colors = if (label == "Email" || label == "Phone" || label == "Name") {
            TextFieldDefaults.outlinedTextFieldColors(
                errorBorderColor = Color.Red,
                errorCursorColor = Color.Red,
                errorLabelColor = Color.Red,
            )
        } else {
            TextFieldDefaults.outlinedTextFieldColors(
                containerColor = whiteColor,
                focusedLabelColor = whiteColor
            )
        },
        singleLine = isSingleLine,
        leadingIcon = {
            when (label) {
                "Email" -> {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null)
                }

                "Phone" -> {
                    Text(text = "+20")
                }

                "Name" -> {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                }

                else -> {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }
            }
        },
        isError = error.value
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: MutableState<String>,
    keyboardType: KeyboardType = KeyboardType.Password,
    isSingleLine: Boolean = true,
    eye: MutableState<Boolean>,
    onButtonAction: () -> Unit,
    onAction: KeyboardActions = KeyboardActions.Default,
    label: String = "Password",
) {
    val visualTransformation = if (eye.value) VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(singleLine = isSingleLine,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        shape = RoundedCornerShape(25.dp),
        label = { Text(text = label) },
        value = password.value,
        onValueChange = {
            if (it.isNotBlank()) {
                password.value = it
            } else {
                password.value = ""
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = { onButtonAction.invoke() }) {
                if (eye.value) Icon(
                    painter = painterResource(id = R.drawable.visibility),
                    contentDescription = null, modifier = Modifier.size(20.dp)
                )
                else Icon(
                    painter = painterResource(id = R.drawable.visibilityoff),
                    contentDescription = null, modifier = Modifier.size(20.dp)
                )
            }
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)

        })
}

@Composable
fun LoginButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick.invoke()
        },
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = redColor
        )
    ) {
        Text(
            text = label,
            color = Color.White
        )
    }
}

@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        if (otpText.length > otpCount) {
            throw IllegalArgumentException("Otp text value must not have more than otpCount: $otpCount characters")
        }
    }

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            if (it.text.length <= otpCount) {
                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = otpText
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index == text.length -> "0"
        index > text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .height(30.dp)
            .border(
                1.dp, when {
                    isFocused -> GreyDark
                    else -> GreyLight
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        text = char,
        style = MaterialTheme.typography.bodyMedium,
        color = if (isFocused) {
            GreyLight
        } else {
            GreyDark
        },
        textAlign = TextAlign.Center
    )
}


@Composable
fun BottomBar(
    selected: MutableState<String>,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        color = mainColor
    ) {
        NavigationBar(containerColor = mainColor, contentColor = mainColor) {
            NavigationBarItem(selected = selected.value == "home", onClick = {
                selected.value = "home"
            }, icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    tint = whiteColor,
                    modifier = Modifier.size(30.dp)
                )
            }, colors = NavigationBarItemDefaults.colors(
                indicatorColor = grayColor
            )
            )

            NavigationBarItem(selected = selected.value == "service", onClick = {
                selected.value = "service"

            }, icon = {
                Icon(
                    painter = painterResource(id = R.drawable.service),
                    contentDescription = null,
                    tint = whiteColor,
                    modifier = Modifier.size(30.dp)
                )
            }, colors = NavigationBarItemDefaults.colors(
                indicatorColor = grayColor
            )
            )

            NavigationBarItem(
                selected = selected.value == "missing", onClick = {
                    selected.value = "missing"

                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.missing),
                        contentDescription = null,
                        tint = whiteColor,
                        modifier = Modifier.size(30.dp)
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    indicatorColor = grayColor
                )
            )
            NavigationBarItem(
                selected = selected.value == "parking", onClick = {
                    selected.value = "parking"

                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.parking),
                        contentDescription = null,
                        tint = whiteColor,
                        modifier = Modifier.size(30.dp)
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    indicatorColor = grayColor
                )
            )
            NavigationBarItem(
                selected = selected.value == "checkout", onClick = {
                    selected.value = "checkout"

                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.checkout),
                        contentDescription = null,
                        tint = whiteColor,
                        modifier = Modifier.size(30.dp)
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    indicatorColor = grayColor
                )
            )
        }
    }
}