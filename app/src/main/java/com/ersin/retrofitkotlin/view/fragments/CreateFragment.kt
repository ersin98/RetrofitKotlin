package com.ersin.retrofitkotlin.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ersin.retrofitkotlin.R
import com.ersin.retrofitkotlin.common.viewBinding
import com.ersin.retrofitkotlin.databinding.FragmentCreateBinding
import com.ersin.retrofitkotlin.databinding.FragmentDetailBinding

class CreateFragment : Fragment(R.layout.fragment_create) {
    private val binding by viewBinding(FragmentCreateBinding::bind)
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            args.product.let {
                if (!it.title.isNullOrEmpty()||! it.title.isBlank()){
                    createTvTitle.hint= it.title
                    createTxtPrice.hint = "${it.price} ₺"
                    createTextDescription.hint = it.description

                    val alphaAnimation = AlphaAnimation(1f, 0.5f)
                    alphaAnimation.duration = 500
                    alphaAnimation.fillAfter = true
                    createImgProduct.startAnimation(alphaAnimation)
                    Glide.with(createImgProduct).load(it.imageData).into(createImgProduct)
                }
                else{
                    createTvTitle.hint= "it.title"
                    createTxtPrice.hint = "it.price"
                    createTextDescription.hint = "it.description"

                    val alphaAnimation = AlphaAnimation(1f, 0.5f)
                    alphaAnimation.duration = 500
                    alphaAnimation.fillAfter = true
                    //createImgProduct.startAnimation(alphaAnimation)
                    //Glide.with(createImgProduct).load(it.imageData).into(createImgProduct)
                }
            }
        }
    }
}