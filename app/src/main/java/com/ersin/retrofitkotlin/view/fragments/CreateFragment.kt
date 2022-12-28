package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.data.model.CreateProductModel
import com.ersin.retrofitkotlin.data.model.ProductModel
import com.ersin.retrofitkotlin.data.service.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentCreateBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CreateFragment : Fragment(R.layout.fragment_create) {
    private val binding by viewBinding(FragmentCreateBinding::bind)
    private val args: DetailFragmentArgs by navArgs()
    private var compositeDisposable: CompositeDisposable?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        with(binding) {
            args.product.let {
                if (!it.title.equals("null")) {
                    createTvTitle.hint = it.title
                    createTxtPrice.hint = it.price.toString()
                    createTextDescription.hint = it.description
                    imgUrl.hint = it.imageData
                } else {
                    imgUrl.hint = "https://imgyukle.com/f/2022/12/28/J13Oa0.jpeg"
                }
                val alphaAnimation = AlphaAnimation(1f, 0.5f)
                alphaAnimation.duration = 100
                alphaAnimation.fillAfter = true
                createImgProduct.startAnimation(alphaAnimation)
                Glide.with(createImgProduct).load(imgUrl.hint).into(createImgProduct)
            }
        }
        //deneme amaçlı hemen yüklüyorum
        val product = CreateProductModel("açıklama","başlık","https://tr.wikipedia.org/wiki/Anasayfa#/media/Dosya:Fratelli_Lumiere.jpg",9.99)
        addData(product)
    }
        fun addData(createProductModel: CreateProductModel){
            val retrofit= Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ProductApiServise::class.java)

                compositeDisposable?.add(retrofit.addProduct(createProductModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addResponse))
        }
        fun addResponse(transactionConfirmation: Boolean){

        }
    }
