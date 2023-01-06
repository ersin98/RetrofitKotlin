package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.data.model.CreateProductRequest
import com.ersin.retrofitkotlin.data.model.EditProductRequest
import com.ersin.retrofitkotlin.data.service.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentDetailBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()
    private var compositeDisposable: CompositeDisposable?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        with(binding) {
            args.product.let {producModel->
                tvTitle.text = producModel.title
                txtPrice.text = "${producModel.price} ₺"
                textDescription.text = producModel.description
                Glide.with(imgProduct).load(producModel.imageData).into(imgProduct)
                edit.setOnClickListener{
                    //val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(producModel)
                    val action= DetailFragmentDirections.actionDetailFragmentToEditFragment(producModel)
                    findNavController().navigate(action)
                    //findNavController().navigate(action)
                }
                delete.setOnClickListener{
                    deleteProduct(producModel.id)
                    val action= DetailFragmentDirections.actionDetailFragmentToMainFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }
    fun deleteProduct(deleteId: Int){
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)

        compositeDisposable?.add(retrofit.deleteProduct(deleteId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())
    }
}