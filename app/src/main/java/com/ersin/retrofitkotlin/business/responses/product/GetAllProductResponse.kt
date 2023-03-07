package com.ersin.retrofitkotlin.business.responses.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class GetAllProductResponse (
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
    @SerializedName("categoryname")
    val categoryName: Int
) : Parcelable