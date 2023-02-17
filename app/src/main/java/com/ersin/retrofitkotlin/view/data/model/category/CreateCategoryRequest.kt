package com.ersin.retrofitkotlin.view.data.model.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateCategoryRequest (
    @SerializedName("name")
    val name:String?
): Parcelable