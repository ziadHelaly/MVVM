package eg.edu.iti.mvvmlab.model.repo

import eg.edu.iti.mvvmlab.model.models.Product
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getProducts(): List<Product>?
    suspend fun getFavourites(): Flow<List<Product>>
    suspend fun addProduct(product: Product): Long
    suspend fun removeProduct(product: Product): Int
}