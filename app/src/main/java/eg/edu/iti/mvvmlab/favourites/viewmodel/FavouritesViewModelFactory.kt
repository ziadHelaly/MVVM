package eg.edu.iti.mvvmlab.favourites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eg.edu.iti.mvvmlab.model.repo.Repository

class FavouritesViewModelFactory (val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouritesViewModel(repository) as T
    }
}