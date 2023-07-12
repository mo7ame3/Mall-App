package com.example.citymall.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.citymall.R
import com.example.citymall.constant.Constant
import com.example.citymall.navigation.AllScreens
import com.example.citymall.sharedpreference.SharedPreference
import com.example.citymall.ui.theme.mainColor
import com.example.citymall.ui.theme.redColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    val context = LocalContext.current
    val sharedPreference = SharedPreference(context)
    val getToken = sharedPreference.getToken.collectAsState(initial = "")

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(
            targetValue = 0.6f,
            animationSpec = tween(
                durationMillis = 800,
                easing = { OvershootInterpolator(8f).getInterpolation(it) })
        )
        delay(500L)
        if (getToken.value.toString().isNotEmpty()) {
            Constant.token = getToken.value.toString()
            navController.navigate(AllScreens.HomeScreen.name) {
                navController.popBackStack()
            }
        } else {
            navController.navigate(AllScreens.LoginScreen.name) {
                navController.popBackStack()
            }
        }
    })

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = mainColor
    )
    {
        Box(contentAlignment = Alignment.Center) {
            Surface(
                modifier = Modifier
                    .size(400.dp, 400.dp)
                    .scale(scale.value),
                color = redColor,
                shape = CircleShape
            )
            {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.shopping_mall),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp, 300.dp)
                    )
                }
            }
        }
    }

}