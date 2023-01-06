package com.ersin.retrofitkotlin.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
    private var compositeDisposable: CompositeDisposable?=null
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
                //deneme amaçlı hemen yüklüyorum
                val product = CreateProductRequest("description","https://www.maxpixel.net/static/photo/1x/Massage-Internet-Plus-Seo-Icon-Social-Add-4608104.png",9.99,"title")
                addProduct(product)


            }
        }
    }
    /*
        fun addProduct(createProductRequest: CreateProductRequest){
            val retrofit= Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ProductApiServise::class.java)

            compositeDisposable?.add(
                retrofit.addProduct(createProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }*//*
    fun addProduct(createProductRequest: CreateProductRequest) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)

        compositeDisposable?.add(
            retrofit.addProduct(createProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {error(it)}
                .subscribe()
        )
    }*/
    fun addProduct(createProductRequest: CreateProductRequest) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)

        compositeDisposable?.add(
            retrofit.addProduct(createProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({// İşlem başarılı ise yapılacak işlemler
                    successful()
                }, { errorinfo ->// İşlem başarısız ise yapılacak işlemler
                    error(errorinfo)
                })
        )
    }
    fun successful(){
        val action= CreateFragmentDirections.actionCreateFragmentToMainFragment()
        findNavController().navigate(action)
    }
    fun error(throwable: Throwable) {
        Toast.makeText(context, "${throwable.message})", Toast.LENGTH_LONG).show()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Hata")
        builder.setMessage(throwable.message)
        builder.setPositiveButton("Tamam") { _, _ -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}


