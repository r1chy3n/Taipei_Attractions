package com.javahand.taipeiattractions.model

import com.squareup.moshi.Json

@Suppress("SpellCheckingInspection")
data class Attraction(
    val id: Int,                // 1
    val name: String,           // 2
    @Json(name = "name_zh")
    val nameZh: String?,        // 3
    @Json(name = "open_status")
    val openStatus: Int,        // 4
    val introduction: String,   // 5
    @Json(name = "open_time")
    val openTime: String,       // 6
    @Json(name = "zipcode")
    val zipCode: String,        // 7
    val distric: String,        // 8
    val address: String,        // 9
    val tel: String,            // 10
    val fax: String,            // 11
    val email: String,          // 12
    val months: String,         // 13
    @Json(name = "nlat")
    val northLatitude: Double,  // 14
    @Json(name = "elong")
    val eastLongitude: Double,      // 15
    @Json(name = "official_site")
    val officialSite: String,       // 16
    val facebook: String,           // 17
    val ticket: String,             // 18
    val remind: String,             // 19
    @Json(name = "staytime")
    val stayTime: String,           // 20
    val modified: String,           // 21
    val url: String,                // 22
    val category: List<Category>,   // 23
    val target: List<Target>,       // 24
    val service: List<Service>,     // 25
    val friendly: List<Friendly>,   // 26
    val images: List<Image>,        // 27
    val files: String,              // 28
    val links: List<Link>           // 29
)
