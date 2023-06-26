package com.javahand.taipeiattractions.data

import com.javahand.taipeiattractions.network.AttractionApi

class AttractionRepository {
    fun articlePagingSource() = AttractionPagingSource(AttractionApi())
} // class AttractionRepository