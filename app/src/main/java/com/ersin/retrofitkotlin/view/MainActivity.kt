package com.ersin.retrofitkotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ersin.retrofitkotlin.adapter.RecyclerViewAdapder
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.databinding.ActivityMainBinding
import com.ersin.retrofitkotlin.model.CryptoModel
import com.ersin.retrofitkotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)
    private  val BASE_URL = "https://raw.githubusercontent.com/"
    private  var cryptoModels:ArrayList<CryptoModel>?=null
    private var recyclerViewAdpder:RecyclerViewAdapder ? = null
    //private val recyclerViewAdapder by lazy { RecyclerViewAdapder() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        val layoutManager:RecyclerView.LayoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager

        loadData()
    }
    private  fun loadData(){
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call= service.getData()
        call.enqueue(object : Callback <List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>,
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels= ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdpder= RecyclerViewAdapder(it)
                            binding.recyclerView.adapter=recyclerViewAdpder
                        }

                        /*
                        for (cryptoModel:CryptoModel in cryptoModels!! )
                        {
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

