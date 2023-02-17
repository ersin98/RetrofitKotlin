package com.ersin.retrofitkotlin.view.data.model.product

import android.os.Parcelable
import com.ersin.retrofitkotlin.view.data.model.category.CategoryModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Locale.Category


@Parcelize
data class ProductModel (
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
    val category: CategoryModel
) : Parcelable