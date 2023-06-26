package com.javahand.taipeiattractions.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.javahand.taipeiattractions.data.AttractionRepository

class AllAttractionsViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: AttractionRepository
): AbstractSavedStateViewModelFactory(owner, null) {

    companion object {
        fun provide(
            owner: SavedStateRegistryOwner
        ): ViewModelProvider.Factory {
            return AllAttractionsViewModelFactory(owner, AttractionRepository())
        } // fun provide( SavedStateRegistryOwner )
    } // companion object

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(AllAttractionsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AllAttractionsViewModel(repository) as T
        } // if

        throw IllegalArgumentException("Unknown ViewModel class")
    } // fun create( String, Class<T>, SavedStateHandle )
} // class AllAttractionsViewModelFactory
