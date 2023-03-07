package com.ersin.retrofitkotlin.business.requests.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class UpdateCategoryRequest(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String
):Parcelable