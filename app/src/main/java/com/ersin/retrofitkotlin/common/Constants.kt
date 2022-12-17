package com.ersin.retrofitkotlin.common

import androidx.lifecycle.MutableLiveData
import com.ersin.retrofitkotlin.data.model.ProductModel

object Constants {
    const val BASE_URL="http://10.0.2.2:8080/"//http://localhost:8080/
    //10.0.2.2
    const val GET_PRODUCTS = "api/products/getall"

    var onProductClick: (ProductModel) -> Unit = {}

    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
}