package com.ersin.retrofitkotlin.view.data.services

import com.ersin.retrofitkotlin.common.Constants.ADD_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.DELETE_ALL_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.DELETE_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.GET_BY_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.GET_BY_QUERY_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.UPDATE_PRODUCTS
import com.ersin.retrofitkotlin.business.requests.product.CreateProductRequest
import com.ersin.retrofitkotlin.business.requests.product.EditProductRequest
import com.ersin.retrofitkotlin.business.responses.product.ProductModel
import io.reactivex.Observable
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ProductApiServise {
    @GET(GET_PRODUCT)
    fun getProduct():Observable<List<ProductModel>>
    //fun getData():Call<List<ProductModel>>
    @GET(GET_BY_CATEGORY)
    fun getByCategory(id: Int):Observable<List<ProductModel>>
    @GET(GET_BY_QUERY_PRODUCT)
    fun searchPoduct(@Query("title") title:String?):Observable<List<ProductModel>>//@PathVariable
/*
    @POST(ADD_PRODUCT)
    fun addProduct(@Body createProductRequest:CreateProductRequest ): Completable
    //createProductRequests: CreateProductRequest
    //createProductRequest:ArrayList<Any>
*/
    //@POST(ADD_PRODUCT)
   // fun addProduct(@Body createProductRequest: CreateProductRequest):CreateProductRequestController
    @POST(ADD_PRODUCT)
    fun addProduct(@Body createProductRequest: CreateProductRequest):Completable
    @DELETE(DELETE_ALL_PRODUCT)
    fun deleteAllProduct():Completable
    @PUT(UPDATE_PRODUCTS)
    fun editProduct(@Body editProductRequest: EditProductRequest):Completable

    @DELETE(DELETE_PRODUCT)
    fun deleteProduct(@Query("id") deleteId:Int):Completable
}