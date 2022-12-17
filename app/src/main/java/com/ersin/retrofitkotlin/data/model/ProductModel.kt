package com.ersin.retrofitkotlin.data.model

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName


data class ProductModel (
    @SerializedName("description")
    val description:String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val name:String,
    @SerializedName("imageData")
    val imageData:String,
    @SerializedName("price")
    val parice: Double
    )