package com.ersin.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.databinding.RowLayoutBinding
import com.ersin.retrofitkotlin.entities.concretes.Product


class RecyclerViewAdapder(private val productList: ArrayList<Product>): RecyclerView.Adapter<RecyclerViewAdapder.RowHolder>() {
    private  val colors : Array<String> = arrayOf("#dcdcdc","#FFE4E1","#F5F5DC","#F0FFFF","#E6E6FA","#D3D3D3","#C0C0C0","#A9A9A9")//hex color codes
    var onProductClick: (Product) -> Unit = {}
    class RowHolder(private val binding: RowLayoutBinding,  private val onProductClick: (Product) -> Unit) :
        RecyclerView.ViewHolder(binding.root)  {

            fun bind(product: Product, colors:Array<String>, position: Int){
            with(binding){
                materyal.setBackgroundColor(Color.parseColor(colors[position%8]))
                textName.text=product.title
                textPrice.text=product.price.toString()
                Glide.with(binding.imageRow).load(product.image).into(binding.imageRow)

                materyal.setOnClickListener{
                    onProductClick(product)//yalnızca id bilgisi verilip ilk listede verilmeyen bilgileri ayrıca da isteyebilirdik.
                }

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        val binding= RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(binding,onProductClick)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(productList[position],colors,position)
    }

    override fun getItemCount(): Int =productList.count()
}