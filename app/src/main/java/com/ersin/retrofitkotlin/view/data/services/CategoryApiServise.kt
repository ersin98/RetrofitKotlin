package com.ersin.retrofitkotlin.view.data.services

import com.ersin.retrofitkotlin.common.Constants.ADD_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.DELETE_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.EDIT_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.GET_CATEGORY
import com.ersin.retrofitkotlin.view.data.model.*
import com.ersin.retrofitkotlin.view.data.model.product.CreateProductRequest
import com.ersin.retrofitkotlin.view.data.model.product.EditProductRequest
import com.ersin.retrofitkotlin.view.data.model.product.ProductModel
import com.ersin.retrofitkotlin.view.data.model.product.ProductResponse
import io.reactivex.Observable
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CategoryApiServise {
    @GET(GET_CATEGORY)
    fun getCategory():Observable<List<ProductModel>>

    @POST(ADD_CATEGORY)
    fun addCategory(@Body createProductRequest: CreateProductRequest):Observable<ProductResponse>

    @POST(EDIT_CATEGORY)
    fun editCategory(@Body editProductRequest: EditProductRequest):Observable<ProductResponse>

    @POST(DELETE_CATEGORY)
    fun deleteCategory(@Query("id") deleteId:Int):Completable
}