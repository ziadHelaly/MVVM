package eg.edu.iti.mvvmlab.model.repo

import eg.edu.iti.mvvmlab.model.local.ProductsLocalDataSource
import eg.edu.iti.mvvmlab.model.models.Product
import eg.edu.iti.mvvmlab.model.remote.ProductsRemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl private constructor(
    private val remote:ProductsRemoteDataSource,
    private val local:ProductsLocalDataSource
) : Repository {

    companion object{
        private var repo:Repository?=null

        fun getInstance(
            remote: ProductsRemoteDataSource,
            local: ProductsLocalDataSource
        ):Repository{
            return repo?: synchronized(this) {
                val instance = RepositoryImpl(remote,local)
                repo=instance
                instance
            }
        }
    }
    override suspend fun getProducts(): List<Product>? {
        return remote.getProducts().body()?.products
    }

    override suspend fun getFavourites(): Flow<List<Product>> {
        return local.getFavouritesProducts()
    }
    override suspend fun removeProduct(product: Product): Int {
        return local.removeProduct(product)
    }
    override suspend fun addProduct(product: Product): Long {
        return local.addProduct(product)
    }
}