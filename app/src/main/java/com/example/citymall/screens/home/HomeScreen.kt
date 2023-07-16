package com.example.citymall.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.citymall.components.BottomBar
import com.example.citymall.components.LoginButton
import com.example.citymall.components.TextInput
import com.example.citymall.constant.Constant.listOfDeals
import com.example.citymall.constant.Constant.listOfOffer
import com.example.citymall.data.ListOfImage
import com.example.citymall.ui.theme.mainColor
import com.example.citymall.ui.theme.redColor
import com.example.citymall.ui.theme.whiteColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val search = remember {
        mutableStateOf("")
    }
    val selectBottomBar = remember {
        mutableStateOf("home")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextInput(
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    input = search,
                    label = "search For stores or more",
                    onAction = KeyboardActions {
                        keyboardController?.hide()
                    })
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = whiteColor
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { /*TODO*/ }) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = whiteColor
                    )
                }
            }
        },
        bottomBar = {
            BottomBar(selected = selectBottomBar)
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize(), color = mainColor) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(80.dp))
                when (selectBottomBar.value) {
                    "home" -> {
                        Home()
                    }

                    "service" -> {
                        Text("service")
                    }

                    "missing" -> {
                        Text("missing")
                    }

                    "parking" -> {
                        Text("parking")
                    }

                    "checkout" -> {
                        Text("checkout")
                    }
                }
            }
        }
    }
}


@Composable
fun Home() {
    val selected = remember {
        mutableStateOf("offer")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        when (selected.value) {
            "offer" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        selected.value = "our"
                    }) {
                        Text(
                            text = "Our Shops",
                            style = MaterialTheme.typography.bodyMedium,
                            color = whiteColor
                        )
                    }
                    TextButton(onClick = {
                        selected.value = "entertainment"
                    }) {
                        Text(
                            text = "Entertainment",
                            style = MaterialTheme.typography.bodyMedium,
                            color = whiteColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Today's Offers",
                    style = MaterialTheme.typography.bodyMedium,
                    color = whiteColor
                )
                Spacer(modifier = Modifier.height(10.dp))
                Offer()
            }

            "our" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        selected.value = "offer"
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = whiteColor
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Our Shops",
                            style = MaterialTheme.typography.bodyLarge,
                            color = whiteColor
                        )
                    }
                }
            }

            "entertainment" -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        selected.value = "offer"
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = whiteColor
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Entertainment",
                            style = MaterialTheme.typography.bodyLarge,
                            color = whiteColor
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun Offer() {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(items = listOfOffer) {
                ImageView(item = it)
                Spacer(modifier = Modifier.width(25.dp))
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Hot Deals",
            style = MaterialTheme.typography.bodyMedium,
            color = whiteColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            items(items = listOfDeals) {
                ImageView(item = it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LoginButton(label = "Plan your visit") {

        }
    }
}

@Composable
fun ImageView(item: ListOfImage) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = item.id), contentDescription = null, modifier =
            Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
        ) {
            Column {
                Surface(
                    shape = RectangleShape,
                    color = redColor
                ) {

                    Text(
                        text = item.label.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = whiteColor
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Surface(
                    shape = RectangleShape,
                    color = redColor
                ) {

                    Text(
                        text = item.ground,
                        style = MaterialTheme.typography.bodyMedium,
                        color = whiteColor
                    )
                }
            }
            Spacer(modifier = Modifier.width(100.dp))
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
            ) {
                Surface(
                    shape = RectangleShape,
                    color = redColor
                ) {

                    Text(
                        text = item.offer.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = whiteColor
                    )
                }
            }
        }

    }
}

