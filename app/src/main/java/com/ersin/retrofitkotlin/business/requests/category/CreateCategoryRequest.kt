package com.ersin.retrofitkotlin.business.requests.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateCategoryRequest (
    @SerializedName("name")
    val name:String
): Parcelable