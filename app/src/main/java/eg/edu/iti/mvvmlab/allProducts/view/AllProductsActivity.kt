package eg.edu.iti.mvvmlab.allProducts.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import eg.edu.iti.mvvmlab.allProducts.ui.theme.MVVMLabTheme
import eg.edu.iti.mvvmlab.allProducts.viewmodel.AllProductsViewModel
import eg.edu.iti.mvvmlab.allProducts.viewmodel.MyFactory
import eg.edu.iti.mvvmlab.model.local.FavouritesDataBase
import eg.edu.iti.mvvmlab.model.local.ProductsLocalDataSource
import eg.edu.iti.mvvmlab.model.models.Product
import eg.edu.iti.mvvmlab.model.remote.ProductsRemoteDataSource
import eg.edu.iti.mvvmlab.model.remote.RetrofitHelper
import eg.edu.iti.mvvmlab.model.repo.RepositoryImpl

class AllProductsActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMLabTheme {
                val viewModel: AllProductsViewModel =
                    viewModel(
                        factory = MyFactory(
                            RepositoryImpl.getInstance(
                                remote = ProductsRemoteDataSource(
                                    RetrofitHelper.serviceApi
                                ),
                                local = ProductsLocalDataSource(
                                    FavouritesDataBase.getInstance(this@AllProductsActivity)
                                        .getDao()
                                )
                            )
                        )
                    )

                ProductsScreen(viewModel = viewModel) {
                    viewModel.addToFav(it)
                }
            }
        }
    }
}

