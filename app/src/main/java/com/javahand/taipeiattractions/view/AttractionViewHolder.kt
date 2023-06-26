package com.javahand.taipeiattractions.view

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javahand.taipeiattractions.databinding.ViewholderAttractionBinding
import com.javahand.taipeiattractions.model.Attraction

class AttractionViewHolder(
    private val binding: ViewholderAttractionBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(attraction: Attraction) {
        attraction.images.firstOrNull()?.run {
            Glide.with(binding.root.context).load(src).into(binding.imageThumb)
        } // let

        binding.textName.text = attraction.name
        binding.textIntro.text = attraction.introduction
        binding.textOpenTime.text = attraction.openTime
    } // fun bind( Attraction )
} // class AttractionItemViewHolder