package com.ersin.retrofitkotlin.service

import com.ersin.retrofitkotlin.common.Constants.GET_PRODUCTS
import io.reactivex.Observable
import com.ersin.retrofitkotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoApiServise {
    @GET(GET_PRODUCTS)
    fun getData():Observable<List<CryptoModel>>
    //fun getData():Call<List<CryptoModel>>
}