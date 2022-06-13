package com.example.seventhweekapppt1.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)


data class HeroesItem(
    @Json(name = "id")
    val id: Int,

    @Json(name = "localized_name")
    val name: String,

    @Json(name = "primary_attr")
    val primaryAttr: String,

    @Json(name = "attack_type")
    val attackType: String,

    @Json(name = "img")
    val img: String,

    ): Serializable