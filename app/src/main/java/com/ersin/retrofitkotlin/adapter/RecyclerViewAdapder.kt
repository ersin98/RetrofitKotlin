package com.ersin.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.databinding.ActivityMainBinding
import com.ersin.retrofitkotlin.databinding.RowLayoutBinding
import com.ersin.retrofitkotlin.model.CryptoModel

class RecyclerViewAdapder(private val cryptoList: ArrayList<CryptoModel>): RecyclerView.Adapter<RecyclerViewAdapder.RowHolder>() {
        private  val colors : Array<String> = arrayOf("#182970","#ff476f","#ea33b1","#c8ac80","#eee9d4","#8fc6c6","#028ca1","#25b5af")//hex color codes
    class RowHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoModel: CryptoModel,colors:Array<String>,position: Int){
            with(binding){
                layout.setBackgroundColor(Color.parseColor(colors[position%8]))
                layout.setOnClickListener{
                    Toast.makeText(it.context, "clicked : ${cryptoModel.currency}", Toast.LENGTH_SHORT).show()
                }
                textName.text=cryptoModel.currency
                textPrice.text=cryptoModel.price
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        val binding= RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position)
    }

    override fun getItemCount(): Int =cryptoList.count()
}