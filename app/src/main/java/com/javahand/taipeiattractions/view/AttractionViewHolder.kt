package com.javahand.taipeiattractions.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javahand.taipeiattractions.databinding.ViewholderAttractionBinding
import com.javahand.taipeiattractions.model.Attraction

class AttractionViewHolder(
    private val binding: ViewholderAttractionBinding,
    private val attractionClickListener: (Attraction) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    fun bind(attraction: Attraction) {
        attraction.images.firstOrNull()?.run {
            if ( src.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(src)
                    .into(binding.imageThumb)
            } // if
        } // let

        binding.textName.text = attraction.name
        binding.textIntro.text = attraction.introduction
        binding.textOpenTime.text = attraction.openTime

        binding.root.setOnClickListener {
            attractionClickListener(attraction)
        } // setOnClickListener
    } // fun bind( Attraction )
} // class AttractionItemViewHolder