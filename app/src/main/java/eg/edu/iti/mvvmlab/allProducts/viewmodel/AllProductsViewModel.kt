package eg.edu.iti.mvvmlab.allProducts.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.edu.iti.mvvmlab.model.models.Product
import eg.edu.iti.mvvmlab.model.models.Response
import eg.edu.iti.mvvmlab.model.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllProductsViewModel(private val repo: Repository) : ViewModel() {
    //    private var _productsList = MutableLiveData<List<Product>?>()
    private var _productsList = MutableStateFlow<Response>(Response.Loading)
    val productList: StateFlow<Response> = _productsList
    private var _message = MutableLiveData<String>()
    var message: LiveData<String> = _message

    /*fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _productsList.value=Response.Loading
            val products = repo.getProducts()
            if (!products.isNullOrEmpty()) {
                _productsList.value=Response.Success(products)
                _message.postValue("Data fetched successfully")
            } else {
                _productsList.value
                _message.postValue("Filed to get data try again")
            }
        }
    }*/
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _productsList.value = Response.Loading
                val products = repo.getProducts()
                if (!products.isNullOrEmpty()) {
                    _productsList.value = Response.Success(products)
//                    _message.postValue("Data fetched successfully")
                } else {
                    _productsList.value=Response.Failure(Exception("Error"))
//                    _message.postValue("Filed to get data try again")
                }

            } catch (e: Exception) {
                _productsList.value=Response.Failure(e)
            }
        }
    }

    fun addToFav(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repo.addProduct(product)
            Log.d("``TAG``", "addToFav: $res")
            if (res > 0) {
                _message.postValue("Added Successfully")
            } else {
                _message.postValue("Already Added")
            }
        }
    }

    fun resetMessage() {
        _message.value = ""
    }
}