package com.assessment.sereneecommerce.data.remote

import com.assessment.sereneecommerce.data.model.Product
import retrofit2.http.GET

interface ProductApiService {
    @GET("products.json")
    suspend fun getProducts(): List<Product>
}
