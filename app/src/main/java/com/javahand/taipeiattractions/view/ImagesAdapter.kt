package com.javahand.taipeiattractions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagesAdapter(
    attractionFragment: AttractionFragment,
    private val imageSrcs: List<String>
): FragmentStateAdapter(attractionFragment) {

    override fun getItemCount(): Int = imageSrcs.size

    override fun createFragment(position: Int): Fragment {
        return ImageFragment().apply {
            arguments = Bundle().also {
                it.putString(IMAGE_SRC, imageSrcs[position])
            } // also
        } // apply
    } // fun createFragment( Int )
} // class ImagesAdapter