package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.adapter.RecyclerViewAdapder
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.data.model.CreateProductRequest
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
            createButton2.setOnClickListener{
                //deneme amaçlı hemen yüklüyorum
                val product = CreateProductRequest("description","https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Ancient_Sasanid_Cataphract_Uther_Oxford_2003_06_2%281%29.jpg/300px-Ancient_Sasanid_Cataphract_Uther_Oxford_2003_06_2%281%29.jpg",9.99,"title")
                addData(product)
            }
        }
    }

    fun addData(createProductRequest: CreateProductRequest){
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)
        compositeDisposable?.add(retrofit.addProduct(createProductRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }
}

