package eg.edu.iti.mvvmlab.favourites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eg.edu.iti.mvvmlab.model.models.Product
import eg.edu.iti.mvvmlab.model.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel(private val repo: Repository) : ViewModel() {
    private var _productsList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>?> = _productsList
    private var _message = MutableLiveData<String>()
    var message: LiveData<String> = _message

    fun getFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getFavourites().collect{
                _productsList.postValue(it)
            }
//            _message.postValue("Data fetched from database")
        }
    }
    fun removeFavourite(product: Product){
        viewModelScope.launch (Dispatchers.IO){
            val res=repo.removeProduct(product)
            if (res>0){
                _message.postValue("Removed Successfully")
            }
//            getFavourites()
        }
    }

    fun resetMessage() {
        _message.value=""
    }
}