package com.assessment.sereneecommerce.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val image: String,
    val name: String,
    val rating: Rating,
    val priceCents: Int,
    val category: String,
    val subCategory: String,
    val keywords: List<String>,
    val description: String
)

@Serializable
data class Rating(
    val stars: Float,
    val count: Int
)
