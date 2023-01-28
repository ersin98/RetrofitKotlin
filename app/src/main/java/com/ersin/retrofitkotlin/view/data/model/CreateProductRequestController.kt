package com.ersin.retrofitkotlin.view.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class CreateProductRequestController(
    @SerializedName("suitable")
    val suitable:Boolean,
    @SerializedName("done")
    val done:Boolean,
    @SerializedName("errorMassage")
    val errorMassage: String
    ) : Parcelable