package com.javahand.taipeiattractions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.javahand.taipeiattractions.databinding.FragmentImageBinding

const val IMAGE_SRC = "imageSrc"

class ImageFragment: Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentImageBinding.inflate(
            inflater,
            container,
            false
        ) // invoke

        return binding.root
    } // fun onCreateView( LayoutInflater, ViewGroup?, Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf {
            it.containsKey(IMAGE_SRC)
        }?.let {
            Glide.with(this)
                .load(it.getString(IMAGE_SRC))
                .into(binding.imageInPager)
        } // let
    } // fun onViewCreated( View, Bundle?)
} // class ImageFragment
