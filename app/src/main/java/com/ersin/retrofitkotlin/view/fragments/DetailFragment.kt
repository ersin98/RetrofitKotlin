package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.data.model.EditProductRequest
import com.ersin.retrofitkotlin.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            args.product.let {producModel->
                tvTitle.text = producModel.title
                txtPrice.text = "${producModel.price} ₺"
                textDescription.text = producModel.description
                Glide.with(imgProduct).load(producModel.imageData).into(imgProduct)
                edit.setOnClickListener{
                    val action = DetailFragmentDirections.actionDetailFragmentToEditFragment(producModel)
                    println("beyaz sayfa: gidiş deneniyor")
                    findNavController().navigate(action)
                }
                delete.setOnClickListener{
                    Toast.makeText(context, "henüz tamamlanmadı", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}