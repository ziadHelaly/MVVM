package eg.edu.iti.mvvmlab.favourites.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import eg.edu.iti.mvvmlab.favourites.ui.theme.MVVMLabTheme
import eg.edu.iti.mvvmlab.favourites.viewmodel.FavouritesViewModel
import eg.edu.iti.mvvmlab.favourites.viewmodel.FavouritesViewModelFactory
import eg.edu.iti.mvvmlab.model.local.FavouritesDataBase
import eg.edu.iti.mvvmlab.model.local.ProductsLocalDataSource
import eg.edu.iti.mvvmlab.model.remote.ProductsRemoteDataSource
import eg.edu.iti.mvvmlab.model.remote.RetrofitHelper
import eg.edu.iti.mvvmlab.model.repo.RepositoryImpl

class FavouritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMLabTheme {
                val viewModel: FavouritesViewModel =
                    viewModel(
                        factory = FavouritesViewModelFactory(
                            RepositoryImpl.getInstance(
                                remote = ProductsRemoteDataSource(
                                    RetrofitHelper.serviceApi
                                ),
                                local = ProductsLocalDataSource(
                                    FavouritesDataBase.getInstance(this@FavouritesActivity)
                                        .getDao()
                                )
                            )
                        )
                    )

                FavouritesScreen(viewModel = viewModel) {
                    viewModel.removeFavourite(it)
                }
            }
        }
    }
}
