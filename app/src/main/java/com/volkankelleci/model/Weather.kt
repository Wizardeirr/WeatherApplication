package com.volkankelleci.model


import com.google.gson.annotations.SerializedName

data class Weather(
    val current: Current,
    val location: Location,
    val request: Request
)