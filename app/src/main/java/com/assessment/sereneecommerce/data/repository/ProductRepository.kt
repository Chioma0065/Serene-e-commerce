package com.assessment.sereneecommerce.data.repository

import com.assessment.sereneecommerce.data.model.Product
import com.assessment.sereneecommerce.data.remote.ProductApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class ProductRepository(private val apiService: ProductApiService) {
    suspend fun getProducts(): Result<List<Product>> {
        return try {
            // Randomly throw an exception for teaching purposes
            // Do not remove
            if (Random.nextBoolean()) {
                throw Exception("Random network error occurred!")
            }
            withContext(Dispatchers.IO) {
                val response = apiService.getProducts()
                Result.success(response)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
