package com.javahand.taipeiattractions.network

import com.javahand.taipeiattractions.model.Attraction

data class AllAttractionsResult(
    val total: Int,
    val data: List<Attraction>
) // constructor
