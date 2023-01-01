package com.ersin.retrofitkotlin.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreateProductRequest (
    @SerializedName("description")
    val description:String,
    @SerializedName("imageData")
    val imageData:String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("title")
    val title:String
    ) : Parcelable