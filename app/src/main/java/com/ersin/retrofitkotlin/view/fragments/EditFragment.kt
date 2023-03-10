package com.ersin.retrofitkotlin.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.view.data.services.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentEditBinding
import com.ersin.retrofitkotlin.business.requests.product.UpdateProductRequest
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
            args.product.let {product->
                editTvTitle.hint = product.title
                editTxtPrice.hint = product.price.toString()
                editTextDescription.hint = product.description
                editImgUrl.hint = product.image

                editButton2.setOnClickListener{
                    editTvTitle.hint = product.title
                    //var product = EditProductRequest(editTextDescription.text.toString(),editImgUrl.text.toString(),editTxtPrice.text.toString().toDouble(),editTvTitle.text.toString(),productModel.id)
                    val updateProduct = UpdateProductRequest(
                    if(!editTextDescription.text.isNullOrEmpty())
                       editTextDescription.text.toString()
                    else product.description,
                    if (!editImgUrl.text.isNullOrEmpty())
                      editImgUrl.text.toString()
                    else product.image,
                    if (!editTxtPrice.text.isNullOrEmpty())
                       editTxtPrice.text.toString().toDouble()
                    else product.price,
                    if(!editTvTitle.text.isNullOrEmpty())
                         editTvTitle.text.toString()
                    else product.title,
                        product.id,
                        product.categoryID//şimdilik aldığı kategorinin id sini geri döndürüyor
                    )
                    editProduct(updateProduct)
                }

                val alphaAnimation = AlphaAnimation(1f, 0.5f)
                alphaAnimation.duration = 100
                alphaAnimation.fillAfter = true
                editImgProduct.startAnimation(alphaAnimation)
                Glide.with(editImgProduct).load(editImgUrl.hint).into(editImgProduct)
            }

        }

    }

    fun editProduct(editProductRequest: UpdateProductRequest){
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)

        compositeDisposable?.add(
            retrofit.editProduct(editProductRequest)
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
}