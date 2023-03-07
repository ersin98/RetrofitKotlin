package com.ersin.retrofitkotlin.entities.concretes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    @SerializedName("description")
    val description:String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("category")
    val categoryID: Int
) : Parcelable