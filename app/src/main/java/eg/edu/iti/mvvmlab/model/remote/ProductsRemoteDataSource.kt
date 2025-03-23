package eg.edu.iti.mvvmlab.model.remote

import eg.edu.iti.mvvmlab.model.models.ProductsResponse
import retrofit2.Response

class ProductsRemoteDataSource (private val api: ServiceApi){
    suspend fun getProducts(): Response<ProductsResponse> {
        return api.getProducts()
    }
}