package com.ersin.retrofitkotlin.view.data.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class EditProductRequest (
    @SerializedName("description")
    var description:String,
    @SerializedName("image")
    var image:String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("title")
    var title:String,
    @SerializedName("id")
    var id:Int
) : Parcelable