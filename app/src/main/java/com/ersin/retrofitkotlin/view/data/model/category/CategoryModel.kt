package com.ersin.retrofitkotlin.view.data.model.category

import android.os.Parcelable
import com.ersin.retrofitkotlin.view.data.model.product.ProductModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize




@Parcelize
data class CategoryModel (
     @SerializedName("category_id")
     val id:Int,
     @SerializedName("category_name")
     val name:String,
     @SerializedName("product")
     val product: List<ProductModel>
) : Parcelable