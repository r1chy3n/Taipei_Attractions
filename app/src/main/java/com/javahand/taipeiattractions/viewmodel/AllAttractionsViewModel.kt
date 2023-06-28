package com.javahand.taipeiattractions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val _attraction = MutableLiveData<Event<Attraction>>()
    val attraction: LiveData<Event<Attraction>>
        get() = _attraction

    val attractions: Flow<PagingData<Attraction>> = Pager(
        config = PagingConfig(
            pageSize = RESULT_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            attractionRepository.attractionPagingSource()
        } // lambda
    ).flow.cachedIn(viewModelScope)

    fun invalidatePagingSource() {
        attractionRepository.invalidatePagingSource()
    } // fun invalidatePagingSource()

    fun attractionClicked(attraction: Attraction) {
        _attraction.value = Event(attraction)
    } // fun setSelectedAttraction( Attraction )
} // class AttractionViewModel
