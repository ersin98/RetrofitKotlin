package com.ersin.retrofitkotlin.entities.concretes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("category_id")
    val id:Int,
    @SerializedName("category_name")
    val name:String,
    @SerializedName("product")
    val product: List<Product>
) : Parcelable
