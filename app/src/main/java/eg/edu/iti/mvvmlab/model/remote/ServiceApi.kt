package eg.edu.iti.mvvmlab.model.remote

import eg.edu.iti.mvvmlab.model.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("products")
    suspend fun getProducts(): Response<ProductsResponse>
}