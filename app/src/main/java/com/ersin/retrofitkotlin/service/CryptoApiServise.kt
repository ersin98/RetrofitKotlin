package com.ersin.retrofitkotlin.service

import com.ersin.retrofitkotlin.common.Constants.GET_CRYPTO
import io.reactivex.Observable
import com.ersin.retrofitkotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoApiServise {
    @GET(GET_CRYPTO)
    fun getData():Observable<List<CryptoModel>>
    //fun getData():Call<List<CryptoModel>>
}