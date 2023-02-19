package com.ersin.retrofitkotlin.view.data.model.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class EditCategoryRequest(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String
):Parcelable