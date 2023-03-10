package com.ersin.retrofitkotlin.view.data.services

import com.ersin.retrofitkotlin.common.Constants.ADD_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.DELETE_ALL_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.DELETE_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.GET_BY_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.GET_BY_QUERY_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCT
import com.ersin.retrofitkotlin.common.Constants.UPDATE_PRODUCTS
import com.ersin.retrofitkotlin.business.requests.product.CreateProductRequest
import com.ersin.retrofitkotlin.business.requests.product.UpdateProductRequest
import com.ersin.retrofitkotlin.business.responses.product.GetAllProductResponse
import com.ersin.retrofitkotlin.business.responses.product.GetByCategoryProductResponse
import com.ersin.retrofitkotlin.business.responses.product.GetByQueryProductResponse
import com.ersin.retrofitkotlin.entities.concretes.Product
import io.reactivex.Observable
import io.reactivex.Completable
import retrofit2.http.*

interface ProductApiServise {
    @GET(GET_PRODUCT)
    fun getProduct():Observable<List<GetAllProductResponse>>
    //fun getData():Call<List<ProductModel>>
    @GET(GET_BY_CATEGORY)
    fun getByCategory(id: Int):Observable<List<GetByCategoryProductResponse>>
    @GET(GET_BY_QUERY_PRODUCT)
    fun searchPoduct(@Query("title") title:String?):Observable<List<GetByQueryProductResponse>>//@PathVariable
/*
    @POST(ADD_PRODUCT)
    fun addProduct(@Body createProductRequest:CreateProductRequest ): Completable
    //createProductRequests: CreateProductRequest
    //createProductRequest:ArrayList<Any>
*/
    //@POST(ADD_PRODUCT)
   // fun addProduct(@Body createProductRequest: CreateProductRequest):CreateProductRequestController
    @POST(ADD_PRODUCT)
    fun createProduct(@Body createProductRequest: CreateProductRequest):Completable
    @DELETE(DELETE_ALL_PRODUCT)
    fun deleteAllProduct():Completable
    @PUT(UPDATE_PRODUCTS)
    fun editProduct(@Body updateProductRequest:UpdateProductRequest):Completable

    @DELETE(DELETE_PRODUCT)
    fun deleteProduct(@Path("id") deleteId:Int):Completable
}