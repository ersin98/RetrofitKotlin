package com.ersin.retrofitkotlin.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.view.data.services.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentEditBinding
import com.ersin.retrofitkotlin.view.data.model.product.EditProductRequest
import com.ersin.retrofitkotlin.view.data.model.product.ProductResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class EditFragment : Fragment(R.layout.fragment_edit) {
    private val binding by viewBinding(FragmentEditBinding::bind)
    private val args: EditFragmentArgs by navArgs()
    private var compositeDisposable: CompositeDisposable?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        with(binding) {
            args.product.let {productModel->
                editTvTitle.hint = productModel.title
                editTxtPrice.hint = productModel.price.toString()
                editTextDescription.hint = productModel.description
                editImgUrl.hint = productModel.image

                editButton2.setOnClickListener{
                    editTvTitle.hint = productModel.title
                    //var product = EditProductRequest(editTextDescription.text.toString(),editImgUrl.text.toString(),editTxtPrice.text.toString().toDouble(),editTvTitle.text.toString(),productModel.id)
                    var product = EditProductRequest("null","null",0.0,"null",productModel.id)

                    if(!editTextDescription.text.isNullOrEmpty()){
                        product.description = editTextDescription.text.toString()
                    }
                    if (!editImgUrl.text.isNullOrEmpty()){
                        product.image = editImgUrl.text.toString()
                    }
                    if (!editTxtPrice.text.isNullOrEmpty()){
                        product.price =editTxtPrice.text.toString().toDouble();
                    }
                    if(!editTvTitle.text.isNullOrEmpty()){
                        product.title = editTvTitle.text.toString()
                    }
                    //deneme amaçlı hemen değiştiriyorum
                    //val product = EditProductRequest("description","https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Ancient_Sasanid_Cataphract_Uther_Oxford_2003_06_2%281%29.jpg/300px-Ancient_Sasanid_Cataphract_Uther_Oxford_2003_06_2%281%29.jpg",9.99,"değiştirildi",productModel.id)
                    editProduct(product)
                }

                val alphaAnimation = AlphaAnimation(1f, 0.5f)
                alphaAnimation.duration = 100
                alphaAnimation.fillAfter = true
                editImgProduct.startAnimation(alphaAnimation)
                Glide.with(editImgProduct).load(editImgUrl.hint).into(editImgProduct)
            }

        }

    }

    fun editProduct(editProductRequest: EditProductRequest){
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)

        compositeDisposable?.add(
            retrofit.editProduct(editProductRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
    }
    fun handleResponse(productResponse : ProductResponse){
        if(productResponse.done){
            val action= EditFragmentDirections.actionEditFragmentToMainFragment()
            findNavController().navigate(action)
            return
        }

        if(productResponse.suitable){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Hata")
            builder.setMessage("güncelleme işlemi yapılamadı")
            builder.setPositiveButton("Tamam") { _, _ -> }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Hatalı Veri Girişi")
            builder.setMessage(productResponse.errorMassage)
            builder.setPositiveButton("Tamam") { _, _ -> }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}