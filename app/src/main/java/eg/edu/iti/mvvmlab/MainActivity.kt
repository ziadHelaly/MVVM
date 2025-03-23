package eg.edu.iti.mvvmlab

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import eg.edu.iti.mvvmlab.allProducts.view.AllProductsActivity
import eg.edu.iti.mvvmlab.favourites.view.FavouritesActivity
import eg.edu.iti.mvvmlab.search.view.SearchActivity
import eg.edu.iti.mvvmlab.ui.theme.MVVMLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMLabTheme {
                MainScreen()
            }
        }
    }
    @Composable
    fun MainScreen(modifier: Modifier = Modifier) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Button(onClick = {
                val intent=Intent(this@MainActivity,AllProductsActivity::class.java)
                startActivity(intent)
            }) {
                Text("All products")
            }
            Button(onClick = {
                val intent=Intent(this@MainActivity,FavouritesActivity::class.java)
                startActivity(intent)
            }) {
                Text("Favourites")
            }
            Button(onClick = {
                val intent=Intent(this@MainActivity,SearchActivity::class.java)
                startActivity(intent)
            }) {
                Text("Search")
            }
            Button(onClick = {
                finish()
            }) {
                Text("Exit")
            }
        }
    }
}
