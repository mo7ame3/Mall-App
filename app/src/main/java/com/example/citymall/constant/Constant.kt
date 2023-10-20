package com.example.citymall.constant

import com.example.citymall.R
import com.example.citymall.data.ListOfImage

object Constant {

    //token
    var token = ""

    //BaseURI
    const val BASE_URI = "https://graduation-project-nrnm.onrender.com/"
    const val LOGIN = "api/v1/users/login"
    const val REGISTER = "api/v1/users/signup"
    const val FORGET_PASSWORD = "api/v1/users/forgetPassword"
    const val RESET_PASSWORD = "api/v1/users/resetPassword"


    val listOfOffer = listOf(
        ListOfImage(
            id = R.drawable.offer_1,
            label = "2D Cinema",
            ground = "Ground Floor",
            offer = "Up to 50% OFF"
        ),
        ListOfImage(
            id = R.drawable.offer_2,
            label = "CHANEL",
            ground = "First Floor",
            offer = "Up to 15% OFF"
        ),
        ListOfImage(
            id = R.drawable.offer_3,
            label = "NIKE",
            ground = "First Floor",
            offer = "Up to 25% OFF"
        ),
    )

    val listOfDeals = listOf(
        ListOfImage(
            id = R.drawable.deal_1,
            label = "Starbucks",
            ground = "Lower Ground Floor",
            offer = "Up to 50% OFF"
        ),
        ListOfImage(
            id = R.drawable.deal_2,
            label = "Mad Over Donnels",
            ground = "Lower Ground Floor",
            offer = "Up to 15% OFF"
        ),
        ListOfImage(
            id = R.drawable.deal_3,
            label = "Waffle",
            ground = "Lower Ground Floor",
            offer = "Up to 15% OFF"
        ),
    )

}