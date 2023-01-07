package com.ersin.retrofitkotlin.view.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class EditProductRequest (
    @SerializedName("description")
    val description:String,
    @SerializedName("imageData")
    val imageData:String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("title")
    val title:String,
    @SerializedName("id")
    val id:Int
) : Parcelable