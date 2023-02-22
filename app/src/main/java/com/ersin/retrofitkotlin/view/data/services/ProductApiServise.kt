package com.ersin.retrofitkotlin.view.data.services

import com.ersin.retrofitkotlin.common.Constants.ADD_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.DELETE_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.EDIT_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCTS
import com.ersin.retrofitkotlin.common.Constants.SEARCH_PRODUCT
import com.ersin.retrofitkotlin.view.data.model.*
import com.ersin.retrofitkotlin.view.data.requests.product.CreateProductRequest
import com.ersin.retrofitkotlin.view.data.requests.product.EditProductRequest
import com.ersin.retrofitkotlin.view.data.responses.product.ProductModel
import com.ersin.retrofitkotlin.view.data.responses.product.ProductResponse
import io.reactivex.Observable
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApiServise {
    @GET(GET_PRODUCTS)
    fun getProduct():Observable<List<ProductModel>>
    //fun getData():Call<List<ProductModel>>
    @GET(SEARCH_PRODUCT)
    fun searchPoduct(@Query("title") title:String?):Observable<List<ProductModel>>
/*
    @POST(ADD_PRODUCT)
    fun addProduct(@Body createProductRequest:CreateProductRequest ): Completable
    //createProductRequests: CreateProductRequest
    //createProductRequest:ArrayList<Any>
*/
    //@POST(ADD_PRODUCT)
   // fun addProduct(@Body createProductRequest: CreateProductRequest):CreateProductRequestController
    @POST(ADD_PRODUCT)
    fun addProduct(@Body createProductRequest: CreateProductRequest):Observable<ProductResponse>

    @POST(EDIT_PRODUCT)
    fun editProduct(@Body editProductRequest: EditProductRequest):Observable<ProductResponse>

    @POST(DELETE_PRODUCT)
    fun deleteProduct(@Query("id") deleteId:Int):Completable

}