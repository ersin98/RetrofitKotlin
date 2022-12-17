package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.adapter.RecyclerViewAdapder
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.databinding.FragmentMainBinding
import com.ersin.retrofitkotlin.data.model.ProductModel
import com.ersin.retrofitkotlin.data.service.ProductApiServise
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment(R.layout.fragment_main) {
private val binding by viewBinding (FragmentMainBinding::bind)
    private var recyclerViewAdapder: RecyclerViewAdapder? = null
    private  var cryptoModels:ArrayList<ProductModel>?=null
    private var compositeDisposable: CompositeDisposable?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        recyclerViewAdapder?.onProductClick={
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        loadData()
    }
         fun loadData(){
            val retrofit= Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ProductApiServise::class.java)
            compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
        }
         fun handleResponse(cryptoList : List<ProductModel>){
            cryptoModels= ArrayList(cryptoList)
            cryptoModels?.let {
                recyclerViewAdapder= RecyclerViewAdapder(it)
                binding.recyclerView.adapter=recyclerViewAdapder
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