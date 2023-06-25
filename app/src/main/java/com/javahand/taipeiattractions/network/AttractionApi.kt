package com.javahand.taipeiattractions.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AttractionApi {
    @Headers("Accept: application/json")
    @GET("{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") pageNumber: Int
    ): AllAttractionsResult

    companion object {
        private const val BASE_URL = "https://www.travel.taipei/open-api/"

        operator fun invoke(): AttractionApi {
            val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            val moshiConverterFactory = MoshiConverterFactory.create(moshi)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .build()
                .create(AttractionApi::class.java)
        } // fun invoke()
    } // companion object
} // interface AttractionApi