package eg.edu.iti.mvvmlab.model.local

import eg.edu.iti.mvvmlab.model.models.Product
import kotlinx.coroutines.flow.Flow

class ProductsLocalDataSource (private val dao: ProductsDao){
    suspend fun getFavouritesProducts(): Flow<List<Product>> {
        return dao.getAll()
    }
    suspend fun removeProduct(product: Product): Int {
        return dao.removeItem(product)
    }
    suspend fun addProduct(product: Product): Long {
        return dao.addProduct(product)
    }
}