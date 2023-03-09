package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.adapter.RecyclerViewAdapder
import com.ersin.retrofitkotlin.common.Constants
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.view.data.services.ProductApiServise
import com.ersin.retrofitkotlin.databinding.FragmentMainBinding
import com.ersin.retrofitkotlin.entities.concretes.Product
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding (FragmentMainBinding::bind)
    private var recyclerViewAdapder: RecyclerViewAdapder? = null
    private  var product:ArrayList<Product>?=null
    private var compositeDisposable: CompositeDisposable?=null

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        loadData()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CompositeDisposable().also { compositeDisposable = it }
        //val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        val gridLayoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = gridLayoutManager
        if (savedInstanceState != null) {
            val savedQuery:String? = savedInstanceState.getString("query")
            savedQuery?.let {
                searchData(savedQuery)
            }
        }
        else{
            loadData()
        }
        binding.serachView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchData(query)
                    savedInstanceState?.putString("query", query)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
        binding.button.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToCreateFragment()
            findNavController().navigate(action)
        }
    }

    fun compositeSameWork(): ProductApiServise {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ProductApiServise::class.java)
        return retrofit
    }
    fun loadData(){
        val retrofit= compositeSameWork()
        compositeDisposable?.add(retrofit.getProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
    }
    fun searchData(searchtitle:String?){
        val retrofit= compositeSameWork()
        compositeDisposable?.add(
            retrofit.searchPoduct(searchtitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
    }
    fun handleResponse(productList : List<Product>){
        product= ArrayList(productList)
        product?.let { productList ->
            recyclerViewAdapder= RecyclerViewAdapder(productList)
            recyclerViewAdapder!!.onProductClick={ product ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(product)
                findNavController().navigate(action)
            }
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

