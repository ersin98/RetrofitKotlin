package com.ersin.retrofitkotlin.data.model

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductModel (
    @SerializedName("description")
    val description:String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("imageData")
    val imageData:String,
    @SerializedName("price")
    val price: Double
    ) : Parcelable