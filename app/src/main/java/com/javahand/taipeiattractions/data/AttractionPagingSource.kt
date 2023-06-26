package com.javahand.taipeiattractions.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.javahand.taipeiattractions.i18n.LanguagePreference
import com.javahand.taipeiattractions.model.Attraction
import com.javahand.taipeiattractions.network.AttractionApi

private const val START_PAGE_NUMBER = 1

const val RESULT_SIZE = 30

class AttractionPagingSource(
    private val attractionApi: AttractionApi
): PagingSource<Int, Attraction>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Attraction> {
        val pageNumber = params.key ?: START_PAGE_NUMBER
        val lang = LanguagePreference.langCode

        return try {
            val result = attractionApi.getAttractions(lang, pageNumber)
            val attractions = result.data
            val prevKey = if (
                pageNumber == START_PAGE_NUMBER
            ) null else pageNumber - 1
            val nextKey = if (
                pageNumber > result.total / RESULT_SIZE
            ) null else pageNumber + 1

            LoadResult.Page(attractions, prevKey, nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } // try - catch
    } // fun load( LoadParams<Int>)

    override fun getRefreshKey(state: PagingState<Int, Attraction>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.run {
                prevKey?.plus(1) ?: nextKey?.minus(1)
            } // run
        } // let
    } // fun getRefreshKey( PagingState<Int, Attraction>)
} // class AttractionPagingSource
