package com.ersin.retrofitkotlin.data.service

import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCTS
import io.reactivex.Observable
import com.ersin.retrofitkotlin.data.model.ProductModel
import retrofit2.http.GET

interface ProductApiServise {
    @GET(GET_PRODUCTS)
    fun getData():Observable<List<ProductModel>>
    //fun getData():Call<List<ProductModel>>
}