package com.javahand.taipeiattractions.data

import com.javahand.taipeiattractions.network.AttractionApi

class AttractionRepository {
    private var attractionPagingSource = attractionPagingSource()
    fun attractionPagingSource(): AttractionPagingSource {
        attractionPagingSource = AttractionPagingSource(AttractionApi())

        return attractionPagingSource
    } // fun attractionPagingSource

    fun invalidatePagingSource() = attractionPagingSource.invalidate()
} // class AttractionRepository