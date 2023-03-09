package com.ersin.retrofitkotlin.view.data.services

import com.ersin.retrofitkotlin.business.requests.category.UpdateCategoryRequest
import com.ersin.retrofitkotlin.common.Constants.ADD_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.DELETE_ALL_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.DELETE_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.GET_CATEGORY
import com.ersin.retrofitkotlin.common.Constants.UPDATE_CATEGORY
import com.ersin.retrofitkotlin.business.responses.category.CategoryModel
import com.ersin.retrofitkotlin.business.requests.product.CreateProductRequest
import io.reactivex.Observable
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CategoryApiServise {
    @GET(GET_CATEGORY)
    fun getCategory():Observable<List<CategoryModel>>

    @POST(ADD_CATEGORY)
    fun addCategory(@Body createProductRequest: CreateProductRequest):Completable

    @POST(UPDATE_CATEGORY)
    fun editCategory(@Body updateProductRequest: UpdateCategoryRequest):Completable

    @DELETE(DELETE_ALL_CATEGORY)
    fun deleteAllCategory():Completable
    @DELETE(DELETE_CATEGORY)
    fun deleteCategory(@Query("id") deleteId:Int):Completable
}