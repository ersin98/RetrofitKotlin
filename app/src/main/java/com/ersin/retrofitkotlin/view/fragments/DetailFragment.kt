package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            args.product.let {
                    tvTitle.text = it.title
                    txtPrice.text = "${it.price} ₺"
                    textDescription.text = it.description
                    Glide.with(imgProduct).load(it.imageData).into(imgProduct)
                    button2.setOnClickListener{
                        val action = DetailFragmentDirections.actionDetailFragmentToCreateFragment(args.product)
                        findNavController().navigate(action)
                     }
            }
        }
    }
}