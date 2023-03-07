package com.ersin.retrofitkotlin.business.responses.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

//bunu değil etities kısmını kullanacak ve response request kullanacak
//daha rahat değiştirebilmek için hemen değiştirmiyorum
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
    val categoryID: Int
) : Parcelable