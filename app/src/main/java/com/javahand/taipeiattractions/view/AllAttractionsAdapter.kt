package com.javahand.taipeiattractions.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.javahand.taipeiattractions.databinding.ViewholderAttractionBinding
import com.javahand.taipeiattractions.model.Attraction

class AllAttractionsAdapter(
    private val attractionClickListener: (Attraction) -> Unit
    ): PagingDataAdapter<Attraction, AttractionViewHolder>(
    ATTRACTION_DIFF_CALLBACK
) {

    companion object {
        private val ATTRACTION_DIFF_CALLBACK = object
            : DiffUtil.ItemCallback<Attraction>() {
            override fun areItemsTheSame(
                oldItem: Attraction,
                newItem: Attraction
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Attraction,
                newItem: Attraction
            ): Boolean = oldItem == newItem
        } // object DiffUtil.ItemCallback<Attraction>
    } // companion object

    override fun onBindViewHolder(
        holder: AttractionViewHolder,
        position: Int
    ) {
        getItem(position)?.let {
            holder.bind(it)
        } // let
    } // fun onBindViewHolder( AttractionViewHolder, Int )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttractionViewHolder = AttractionViewHolder(
        ViewholderAttractionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        attractionClickListener
    ) // constructor
} // class AllAttractionsAdapter
