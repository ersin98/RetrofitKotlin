package com.ersin.retrofitkotlin.business.responses.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAllCategoryResponse (
    @SerializedName("category_id")
    val id:Int,
    @SerializedName("category_name")
    val name:String,
) : Parcelable