package com.ersin.retrofitkotlin.model

import com.google.gson.annotations.SerializedName


data class CryptoModel (
    @SerializedName("description")
    val currency:String,
    @SerializedName("title")
    val price:String
    )