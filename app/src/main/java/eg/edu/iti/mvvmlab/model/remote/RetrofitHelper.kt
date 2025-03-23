package eg.edu.iti.mvvmlab.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://dummyjson.com/"
    val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val serviceApi= retrofitInstance.create(ServiceApi::class.java)
}