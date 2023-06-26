package com.javahand.taipeiattractions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.javahand.taipeiattractions.data.AttractionRepository
import com.javahand.taipeiattractions.data.RESULT_SIZE
import com.javahand.taipeiattractions.model.Attraction
import kotlinx.coroutines.flow.Flow

class AllAttractionsViewModel(
    private val attractionRepository: AttractionRepository
): ViewModel() {
    val attractions: Flow<PagingData<Attraction>> = Pager(
        config = PagingConfig(
            pageSize = RESULT_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            attractionRepository.articlePagingSource()
        } // lambda
    ).flow.cachedIn(viewModelScope)
} // class AttractionViewModel
