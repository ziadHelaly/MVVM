package eg.edu.iti.mvvmlab.allProducts.view

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import eg.edu.iti.mvvmlab.allProducts.viewmodel.AllProductsViewModel
import eg.edu.iti.mvvmlab.model.models.Product
import eg.edu.iti.mvvmlab.model.models.Response

/*@Composable
fun ProductsScreen(
    viewModel: AllProductsViewModel,
    modifier: Modifier = Modifier,
    onClick: (Product) -> Unit
) {

    val products = viewModel.productList.observeAsState()
    val isLoading = products.value?.isEmpty() ?: true
    val message = viewModel.message.observeAsState()
    val context = LocalContext.current

    //On configurations changed launched effect launched again
    LaunchedEffect(true) {
        if (products.value.isNullOrEmpty()){
            viewModel.getData()
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products.value!!) { prod ->
                ProductItem(prod, onClick)
            }
        }
    }
    LaunchedEffect(message.value) {
        if (!message.value.isNullOrEmpty()) {
            Toast.makeText(context, message.value, Toast.LENGTH_SHORT).show()
            viewModel.resetMessage()
        }
    }
}*/
@Composable
fun ProductsScreen(
    viewModel: AllProductsViewModel,
    modifier: Modifier = Modifier,
    onClick: (Product) -> Unit
) {
    val productsState by viewModel.productList.collectAsStateWithLifecycle() // ✅ Using `by` to avoid `.value`
    val context = LocalContext.current

    // Fetch data on first composition
    LaunchedEffect(Unit) {
        viewModel.getData()
    }

    when (productsState) {
        is Response.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Response.Success -> {
            val products = (productsState as Response.Success).data
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(products) { prod ->
                    ProductItem(prod, onClick)
                }
            }
        }

        is Response.Failure -> {
            val errorMessage = (productsState as Response.Failure).error.message
            LaunchedEffect(errorMessage) {
                Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProductItem(
    product: Product,
    onClick: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        GlideImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(150.dp)
                .clip(CircleShape)
        )
        Column {
            Text(product.title ?: "", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier.size(16.dp))
            Text(product.category ?: "")
            Spacer(modifier.size(16.dp))
            Button(
                onClick = { onClick(product) },
            ) {
                Text("Add to favourite")
            }
        }
    }

}

