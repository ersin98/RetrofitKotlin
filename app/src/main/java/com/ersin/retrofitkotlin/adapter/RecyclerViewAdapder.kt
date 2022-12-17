package com.ersin.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.common.Constants.onProductClick
import com.ersin.retrofitkotlin.databinding.RowLayoutBinding
import com.ersin.retrofitkotlin.data.model.ProductModel


class RecyclerViewAdapder(private val cryptoList: ArrayList<ProductModel>): RecyclerView.Adapter<RecyclerViewAdapder.RowHolder>() {
        private  val colors : Array<String> = arrayOf("#dcdcdc","#FFE4E1","#F5F5DC","#F0FFFF","#E6E6FA","#D3D3D3","#C0C0C0","#A9A9A9")//hex color codes
    class RowHolder(private val binding: RowLayoutBinding,  private val onProductClick: (ProductModel) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel, colors:Array<String>, position: Int){
            with(binding){
                materyal.setBackgroundColor(Color.parseColor(colors[position%8]))
                materyal.setOnClickListener{
                    Toast.makeText(it.context, "clicked : ${productModel.name}", Toast.LENGTH_SHORT).show()
                }
                textName.text=productModel.name
                textPrice.text=productModel.parice.toString()
                Glide.with(binding.imageRow).load(productModel.imageData).into(binding.imageRow)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        val binding= RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding,onProductClick)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position)
    }

    override fun getItemCount(): Int =cryptoList.count()
}