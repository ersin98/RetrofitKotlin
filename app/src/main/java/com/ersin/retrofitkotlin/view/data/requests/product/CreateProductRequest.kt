package com.ersin.retrofitkotlin.view.data.requests.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreateProductRequest (
    @SerializedName("description")
    val description:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("title")
    val title:String
) : Parcelable