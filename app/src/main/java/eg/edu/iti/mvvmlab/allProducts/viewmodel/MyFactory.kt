package eg.edu.iti.mvvmlab.allProducts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.edu.iti.mvvmlab.model.repo.Repository

class MyFactory (val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllProductsViewModel(repository) as T
    }
}