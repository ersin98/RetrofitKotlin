package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.adapter.RecyclerViewAdapder
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.databinding.FragmentMainBinding
import com.ersin.retrofitkotlin.model.CryptoModel
import com.ersin.retrofitkotlin.service.CryptoApiServise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(R.layout.fragment_main) {
private val binding by viewBinding (FragmentMainBinding::bind)
    private var recyclerViewAdpder: RecyclerViewAdapder? = null
    private  var cryptoModels:ArrayList<CryptoModel>?=null
    private var compositeDisposable: CompositeDisposable?=null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager
        loadData()
    }
         fun loadData(){
            val retrofit= Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(CryptoApiServise::class.java)
            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
        }
         fun handleResponse(cryptoList : List<CryptoModel>){
            cryptoModels= ArrayList(cryptoList)
            cryptoModels?.let {
                recyclerViewAdpder= RecyclerViewAdapder(it)
                binding.recyclerView.adapter=recyclerViewAdpder
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            compositeDisposable?.clear()
        }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}