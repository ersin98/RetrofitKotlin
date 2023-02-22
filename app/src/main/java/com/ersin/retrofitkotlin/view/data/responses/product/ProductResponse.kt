package com.ersin.retrofitkotlin.view.data.responses.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProductResponse(
    @SerializedName("suitable")
    val suitable:Boolean,
    @SerializedName("done")
    val done:Boolean,
    @SerializedName("errorMassage")
    val errorMassage: String
    ) : Parcelable