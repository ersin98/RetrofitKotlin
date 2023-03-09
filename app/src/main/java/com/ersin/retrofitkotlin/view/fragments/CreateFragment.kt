package com.ersin.retrofitkotlin.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.view.data.services.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentCreateBinding
import com.ersin.retrofitkotlin.business.requests.product.CreateProductRequest
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CreateFragment : Fragment(R.layout.fragment_create) {
    private val binding by viewBinding(FragmentCreateBinding::bind)
    private var compositeDisposable: CompositeDisposable?=null
    //private  var productResponseModels:ArrayList<ProductResponse>?=null//liste yerine tek nesne geldiği için gerek yok

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CompositeDisposable().also { compositeDisposable = it }
       with(binding) {
            imgUrl.hint = "https://www.maxpixel.net/static/photo/1x/Massage-Internet-Plus-Seo-Icon-Social-Add-4608104.png"
            val alphaAnimation = AlphaAnimation(1f, 0.5f)
            alphaAnimation.duration = 100
            alphaAnimation.fillAfter = true
            createImgProduct.startAnimation(alphaAnimation)
            Glide.with(createImgProduct).load(imgUrl.hint).into(createImgProduct)
            createButton2.setOnClickListener{
                //val title1 =createTvTitle.text?.toString() ?: "null"//olmaz çünkü toString null döndürüyor
                //val title = if (!createTvTitle.text.toString().isNullOrEmpty()) createTvTitle.text.toString() else "null"

                val product = CreateProductRequest(
                    if (!createTextDescription.text.isNullOrEmpty())createTextDescription.text.toString() else null,
                    if (!imgUrl.text.isNullOrEmpty())imgUrl.text.toString() else null,
                    if (!createTxtPrice.text.isNullOrEmpty()) createTxtPrice.text.toString().toDouble() else null,
                    if (!createTvTitle.text.isNullOrEmpty()) createTvTitle.text.toString() else null,
                    5//şimilik hemen veryorum
//categoryıd
                )
                addProduct(product)
            }
        }
    }
    fun error(){

    }

    fun addProduct(createProductRequest: CreateProductRequest) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductApiServise::class.java)
        compositeDisposable?.add(
            retrofit.createProduct(createProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // İşlem başarılı oldu
                    Toast.makeText(context, "Product created successfully", Toast.LENGTH_SHORT).show()
                }, { error ->
                    // Hata oluştu
                    Toast.makeText(context, "Error creating product: ${error.message}", Toast.LENGTH_SHORT).show()
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }
}

