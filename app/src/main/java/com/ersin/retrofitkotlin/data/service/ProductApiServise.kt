package com.ersin.retrofitkotlin.data.service

import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCTS
import com.ersin.retrofitkotlin.common.Constants.SEARCH_PRODUCT
import io.reactivex.Observable
import com.ersin.retrofitkotlin.data.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApiServise {
    @GET(GET_PRODUCTS)
    fun getData():Observable<List<ProductModel>>
    //fun getData():Call<List<ProductModel>>
    @GET(SEARCH_PRODUCT)
    fun searchPoduct(@Query("title") title:String):Observable<List<ProductModel>>
}